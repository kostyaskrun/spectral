DELETE FROM users_roles;
DELETE FROM users;
DELETE FROM countries;
DELETE FROM roles;

INSERT INTO countries (id, short_code, name, created_at, updated_at)
VALUES (1, 'AF', 'AFGHANISTAN', current_timestamp, current_timestamp),
       (2, 'AL', 'ALBANIA', current_timestamp, current_timestamp),
       (3, 'DZ', 'ALGERIA', current_timestamp, current_timestamp);

INSERT INTO roles (id, name, created_at, updated_at)
VALUES (1, 'admin', current_timestamp, current_timestamp),
       (2, 'user', current_timestamp, current_timestamp),
       (3, 'moderator', current_timestamp, current_timestamp);

INSERT INTO users (id, name, email, country_id, created_at, updated_at)
VALUES (1, 'Vasya', 'vasya@gmail.com', 1, '2020-05-20 20:24:55.000000', '2020-05-21 20:24:55.000000'),
       (2, 'Vanya', 'vanya@gmail.com', 2, '2020-05-21 20:24:55.000000', '2020-05-21 20:24:55.000000');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (2, 3);

ALTER SEQUENCE countries_id_seq RESTART WITH 4;
ALTER SEQUENCE roles_id_seq RESTART WITH 4;
ALTER SEQUENCE users_id_seq RESTART WITH 3;