DELETE FROM user;
DELETE FROM RESTAURANT;
DELETE FROM DISH;
DELETE FROM USER_VOTE;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO user (name, email, password, isadmin) VALUES
  ('User1', 'user1@yandex.ru', 'password', FALSE),
  ('User2', 'user2@yandex.ru', 'password', FALSE),
  ('Admin', 'admin@gmail.com', 'admin', TRUE );

INSERT INTO RESTAURANT (NAME, ADDRESS) VALUES
  ('Ginza', 'Sadovaya 12'),
  ('Teremok', 'Nevskij 10');

INSERT INTO DISH (NAME, PRICE, RESTAURANT_ID, DATE) VALUES
  ('Borsch', 2.5, 100003, '2018-03-26'),
  ('Cutlet', 1.75, 100003, '2018-03-26'),
  ('Stewed fruit', 0.55, 100003, '2018-03-26'),
  ('Saltwort', 2.6, 100004, '2018-03-26'),
  ('Cutlet', 1.75, 100004, '2018-03-26'),
  ('Orange juice', 0.4, 100004, '2018-03-26');


