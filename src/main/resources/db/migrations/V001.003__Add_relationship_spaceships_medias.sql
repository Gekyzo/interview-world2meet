ALTER TABLE spaceships
    DROP COLUMN appears_in,
    ADD COLUMN media_id UUID,
    ADD CONSTRAINT fk_media FOREIGN KEY (media_id) REFERENCES medias (id);
