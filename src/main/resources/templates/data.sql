INSERT INTO "genre" ("name")
VALUES ('Comedy'),
       ('Drama'),
       ('Cartoon'),
       ('Triller'),
       ('Documental'),
       ('Action');

INSERT INTO "mpa_rating" ("name")
VALUES ('G'),
       ('PG'),
       ('PG-13'),
       ('R'),
       ('NC-17');

INSERT INTO "film" ("name", "description", "release_date", "duration", "genre_id", "mpa_rating_id")
VALUES ('The Great Adventure', 'An exciting tale of a young hero embarking on a perilous journey to save the world.',
        '2023-07-15', 120, 1, 3),
       ('Deep Silence', 'A drama exploring the depths of human emotion during a crisis.', '2022-11-05', 135, 2, 2),
       ('Funny Bones', 'A comedy about two clumsy brothers who try to open a circus.', '2024-01-20', 90, 1, 1),
       ('Dark Waters', 'A thrilling psychological story set in the dark corners of the mind.', '2021-09-10', 110, 4, 4),
       ('Space Voyage', 'A sci-fi action movie about astronauts facing a deadly encounter in space.', '2025-05-25', 150,
        6, 3),
       ('The Last Dance', 'A documentary showcasing the life of the most famous ballet dancer.', '2020-12-12', 90, 5,
        2),
       ('Epic Quest', 'A fantasy adventure where an ancient hero fights against evil forces to protect the realm.',
        '2023-03-22', 140, 3, 2),
       ('The Escape', 'A thrilling escape movie full of action and suspense.', '2024-08-17', 130, 6, 4);

INSERT INTO "user" ("email", "login", "name", "birthday")
VALUES ('john.doe@example.com', 'johndoe', 'John Doe', '1990-05-15'),
       ('jane.smith@example.com', 'janesmith', 'Jane Smith', '1985-09-22'),
       ('alice.jones@example.com', 'alicejones', 'Alice Jones', '1992-11-30'),
       ('bob.brown@example.com', 'bobbrown', 'Bob Brown', '1988-02-05'),
       ('charlie.davis@example.com', 'charliedavis', 'Charlie Davis', '2000-03-25');

INSERT INTO "friend" ("follower_id", "followed_id", "status")
VALUES (SELECT "id" FROM "user" WHERE "email" = 'bob.brown@example.com',
        SELECT "id" FROM "user" WHERE "email" = 'charlie.davis@example.com',
        true),
       (SELECT "id" FROM "user" WHERE "email" = 'charlie.davis@example.com',
        SELECT "id" FROM "user" WHERE "login" = 'alicejones',
        'false');