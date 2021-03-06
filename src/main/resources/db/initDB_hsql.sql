DROP TABLE user IF EXISTS;
DROP TABLE restaurant IF EXISTS;
DROP TABLE dish IF EXISTS;
DROP TABLE user_vote IF EXISTS;
DROP SEQUENCE GLOBAL_SEQ IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE user
(
  id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name             VARCHAR(255)            NOT NULL,
  email            VARCHAR(255)            NOT NULL,
  password         VARCHAR(255)            NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOLEAN DEFAULT TRUE    NOT NULL,
  isAdmin          BOOLEAN DEFAULT FALSE   NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON USER (email);

CREATE TABLE restaurant
(
  id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name             VARCHAR(255)            NOT NULL,
  address          VARCHAR(255)            NOT NULL
);

CREATE TABLE dish
(
  id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name             VARCHAR(255)                NOT NULL,
  price            NUMERIC(5,2)                NOT NULL,
  restaurant_id    INTEGER                     NOT NULL,
  date             DATE DEFAULT current_date   NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurant(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX dish_unique_idx ON dish (restaurant_id, date, name);

CREATE TABLE user_vote
(
  id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  user_id          INTEGER                    NOT NULL,
  restaurant_id    INTEGER                    NOT NULL,
  date             DATE DEFAULT current_date  NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES RESTAURANT (id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES USER (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX uservote_unique_restaurant_user_date_idx ON user_vote (user_id, date);



