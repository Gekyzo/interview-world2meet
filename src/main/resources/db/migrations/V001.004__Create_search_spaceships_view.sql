CREATE VIEW spaceships_search AS
SELECT s.id, s.name, s.captain_name, s.length, s.max_speed, m.name AS appears_in
FROM spaceships s
         LEFT JOIN medias m ON s.media_id = m.id;