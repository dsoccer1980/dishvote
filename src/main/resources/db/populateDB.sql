DELETE FROM user;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO user (name, email, password, isadmin) VALUES
  ('User', 'user@yandex.ru', 'password', FALSE),
  ('Admin', 'admin@gmail.com', 'admin', TRUE );


