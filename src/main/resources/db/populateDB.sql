DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES
       ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES
       ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES
       ('2019-10-18 09:00', 'testFirst', 5000, 100000),
       ('2019-10-20 09:01', 'testSecond', 7000, 100000);
--        ('2019-10-18 09:00', 'breakfast', 500, 100000),
--        ('2019-10-18 12:00', 'lunch', 1000, 100000),
--        ('2019-10-18 18:00', 'dinner', 600, 100000),
--        ('2019-10-19 08:30', 'breakfast', 500, 100000),
--        ('2019-10-19 13:30', 'lunch', 500, 100000),
--        ('2019-10-19 20:15', 'diner', 250, 100000),
--
--        ('2019-10-15 09:00', 'breakfast', 250, 100001),
--        ('2019-10-15 12:00', 'lunch', 1000, 100001),
--        ('2019-10-15 17:00', 'dinner', 750, 100001),
--        ('2019-10-16 10:30', 'breakfast', 250, 100001),
--        ('2019-10-16 14:00', 'lunch', 250, 100001),
--        ('2019-10-16 18:00', 'dinner', 250, 100001);

