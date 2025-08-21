\connect appdb;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS organization (
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR (30) NOT NULL
);


