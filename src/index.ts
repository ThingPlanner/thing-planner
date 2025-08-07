import express, { Request, Response } from "express";
import session from "express-session";
import Keycloak, { KeycloakConfig } from "keycloak-connect";
import dotenv from "dotenv";
import swaggerUi from "swagger-ui-express";
import swaggerDocument from './swagger.json';

dotenv.config();

const app = express();
const port = 8081;

const memoryStore = new session.MemoryStore();

app.use(
    session({
        secret: "super-secret-key", // Replace with a strong, random string in production
        resave: false,
        saveUninitialized: true,
        store: memoryStore,
        cookie: {
            httpOnly: true,
            secure: false,
        }
    })
);

const keycloakConfig: KeycloakConfig = {
    "confidential-port": 0,
    "auth-server-url": "http://localhost:8080/+",
    "resource": "account",
    "ssl-required": "external",
    "bearer-only": true,
    "realm": "thingplanner",
};

const keycloak = new Keycloak({ store: memoryStore }, keycloakConfig);

app.use(keycloak.middleware());

app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerDocument));

app.get("/", (req: Request, res: Response) => {
    res.send("Hello, world! This is a public route." )
});

app.get("/protected",
    keycloak.protect(),
        (req: Request, res: Response) => {
        res.send("Access successful");
    });

app.listen(port, () => {
    console.log(`Server is running at http://localhost:${port}`);
})