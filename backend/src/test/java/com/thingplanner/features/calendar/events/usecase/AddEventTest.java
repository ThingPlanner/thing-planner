package com.thingplanner.features.calendar.events.usecase;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import io.quarkus.test.InjectMock;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class AddEventTest {

    @InjectMock
    AddService addService;

    @Test
    void testMalformedRequestBehaviour() {
        String malformedJson = """
                {
                    "name": {--;},
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(malformedJson)
                .when()
                .post("/events/add")
                .then()
                .statusCode(400);
    }

    @Test
    void testInvalidRequestReturnsErrorMessage() {
        String malformedJson = """
                    {
                    "name": "'A nice holiday event' OR '1'='1",
                    "eventType": { "id": 1, "name": "Holiday"},
                    "startDateTime": "2025-12-03T10:15:30+01:00[Europe/Paris]",
                    "endDateTime": "2025-12-03T11:15:30+01:00[Europe/Paris]"
                }
            """;

        given()
                .contentType(ContentType.JSON)
                .body(malformedJson)
                .when()
                .post("/events/add")
                .then()
                .statusCode(400)
                .body("message", equalTo("Could not add new event."));
    }
}