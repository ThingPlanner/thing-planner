package com.thingplanner.backend.events.feature;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AddEventApi.class)
public class AddEventTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AddService addService;

    @Test
    void testMalformedRequestBehaviour() throws Exception {
        String malformedJson = """
                {
                    "name": {--;},
                }
                """;

        mockMvc.perform(post("/events/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(malformedJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testInvalidRequestReturnsErrorMessage() throws Exception {
        String malformedJson = """
                    {
                    "name": "'A nice holiday event' OR '1'='1",
                    "eventType": { "id": 1, "name": "Holiday"},
                    "startDateTime": "2025-12-03T10:15:30+01:00[Europe/Paris]",
                    "endDateTime": "2025-12-03T11:15:30+01:00[Europe/Paris]"
                }
            """;

        mockMvc.perform(post("/events/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(malformedJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Could not add new event."));
    }
}
