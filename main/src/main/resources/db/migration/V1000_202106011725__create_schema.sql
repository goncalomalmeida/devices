DO
$$
    BEGIN
        CREATE TABLE IF NOT EXISTS devices
        (
            id BIGSERIAL NOT NULL,
            name TEXT NOT NULL,
            brand TEXT NOT NULL,
            creation_time TIMESTAMP NOT NULL,
            CONSTRAINT devices_pkey PRIMARY KEY (id)
        );
    END;
$$
