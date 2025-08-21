\connect appdb;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS organization (
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR (30) NOT NULL
);

CREATE TABLE IF NOT EXISTS organizations_events (
    organization_id UUID NOT NULL,
    event_id UUID NOT NULL,
    PRIMARY KEY (organization_id, event_id),
    CONSTRAINT fk_org
        FOREIGN KEY (organization_id) REFERENCES organization(id)
        ON DELETE CASCADE ,
    CONSTRAINT fk_event
        FOREIGN KEY (event_id) REFERENCES events (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS events (
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL,
    start_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
    end_date_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS organizations_pages (
    organization_id UUID NOT NULL,
    event_id UUID NOT NULL,
    PRIMARY KEY (organization_id, event_id),
    CONSTRAINT fk_org
        FOREIGN KEY (organization_id) REFERENCES organization (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_event
        FOREIGN KEY (event_id) REFERENCES events (id)
        ON DELETE CASCADE
);

