DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS passport_black_list;
DROP TABLE IF EXISTS check_result;
DROP TABLE IF EXISTS phone_black_list;
DROP TABLE IF EXISTS address_black_list;
DROP TABLE IF EXISTS client_address;
DROP TABLE IF EXISTS offer;
DROP TABLE IF EXISTS client_status;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS client;

CREATE TABLE client
(
    id          BIGINT NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    first_name  VARCHAR NOT NULL,
    middle_name VARCHAR,
    second_name VARCHAR NOT NULL,
    passport    VARCHAR NOT NULL,
    phone       VARCHAR NOT NULL
);

CREATE TABLE offer
(
    id            BIGINT NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    client_id     BIGINT NOT NULL,
    date_of_offer TIMESTAMP NOT NULL,
    date_of_check TIMESTAMP,
    type          VARCHAR NOT NULL,
    amount        DECIMAL NOT NULL,
    testvar   INTEGER ,
    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE
);

CREATE TABLE client_status
(
    id        BIGINT NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    client_id BIGINT NOT NULL,
    level     VARCHAR,
    regular   BOOLEAN,
    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE
);

CREATE TABLE address
(
    id   BIGINT NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR NOT NULL
);

CREATE TABLE client_address
(
    client_id  BIGINT REFERENCES client (id) ON DELETE CASCADE,
    address_id BIGINT REFERENCES address (id),
    PRIMARY KEY (client_id, address_id)
);

CREATE TABLE passport_black_list
(
    id     BIGINT NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    number VARCHAR NOT NULL
);

CREATE TABLE phone_black_list
(
    id     BIGINT NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    number VARCHAR NOT NULL
);

CREATE TABLE address_black_list
(
    id   BIGINT NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR NOT NULL
);

CREATE TABLE check_result
(
    id               BIGINT NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    offer_id         BIGINT NULL,
    phone_approve    BOOLEAN NULL,
    passport_approve BOOLEAN NULL,
    address_approve  BOOLEAN NULL,
    report           BOOLEAN NULL,
    result           VARCHAR NULL
);

CREATE TABLE users
(
    id               BIGINT NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name             VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    email            VARCHAR                           NOT NULL
);

CREATE TABLE roles
(
    id      INTEGER NOT NULL,
    name    VARCHAR
);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

