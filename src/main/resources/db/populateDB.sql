DELETE FROM user;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO user (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');


