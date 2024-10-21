CREATE TABLE IF NOT EXISTS medias
(
    id   UUID PRIMARY KEY,
    name VARCHAR(255),
    CONSTRAINT uq_media_name UNIQUE (name)
);