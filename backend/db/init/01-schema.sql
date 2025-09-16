\connect appdb;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS organization (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR (30) NOT NULL
);

CREATE TABLE IF NOT EXISTS things (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR (30) NOT NULL
);

CREATE TABLE IF NOT EXISTS event_types (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS events (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL,
    event_type_id BIGINT,
    CONSTRAINT fk_event_type
        FOREIGN KEY (event_type_id) REFERENCES event_types (id)
        ON DELETE CASCADE,
    start_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
    end_date_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS pages (
    id UUID DEFAULT uuid_generate_v4(),
    title VARCHAR(50) NOT NULL,
    thing_id UUID DEFAULT uuid_generate_v4(),
    organization_id UUID DEFAULT uuid_generate_v4(),
    PRIMARY KEY (id, thing_id, organization_id),
    CONSTRAINT fk_thing
        FOREIGN KEY (thing_id) REFERENCES things (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_org
        FOREIGN KEY (organization_id) REFERENCES organization (id)
        ON DELETE CASCADE,
    parent_id UUID,
    url TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS organizations_events (
    organization_id UUID NOT NULL,
    event_id UUID NOT NULL,
    PRIMARY KEY (organization_id, event_id),
    CONSTRAINT fk_org
        FOREIGN KEY (organization_id) REFERENCES organization (id)
        ON DELETE CASCADE ,
    CONSTRAINT fk_event
        FOREIGN KEY (event_id) REFERENCES events (id)
        ON DELETE CASCADE
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