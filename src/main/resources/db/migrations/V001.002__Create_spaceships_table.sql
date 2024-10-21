CREATE TABLE IF NOT EXISTS spaceships
(
    id           UUID PRIMARY KEY,
    name         VARCHAR(255),
    captain_name VARCHAR(255),
    length       DOUBLE PRECISION,
    max_speed    INTEGER,
    media_id     UUID,
    CONSTRAINT uq_spaceship_name UNIQUE (name),
    CONSTRAINT fk_media FOREIGN KEY (media_id) REFERENCES medias (id)
);