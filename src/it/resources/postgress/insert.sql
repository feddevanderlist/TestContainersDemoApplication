CREATE table IF NOT EXISTS gebruiker
(
    id
    SERIAL
    PRIMARY
    KEY,
    voornaam
    varchar,
    achternaam
    varchar,
    leeftijd
    int,
    titel
    varchar,
    is_admin
    Boolean
);
CREATE table IF NOT EXISTS genre
(
    id
    SERIAL
    PRIMARY
    KEY,
    name
    varchar
);
CREATE table IF NOT EXISTS language
(
    id
    SERIAL
    PRIMARY
    KEY,
    name
    varchar
);

CREATE table IF NOT EXISTS country
(
    id
    SERIAL
    PRIMARY
    KEY,
    name
    varchar,
    capital
    varchar
);
CREATE table IF NOT EXISTS book
(
    id
    SERIAL
    PRIMARY
    KEY,
    title
    varchar,
    author_id
    bigint


);


CREATE table IF NOT EXISTS author
(
    id
    SERIAL
    PRIMARY
    KEY,
    first_name
    varchar,
    last_name
    varchar,
    country_of_origin
    bigint,
    country_of_residence
    bigint,
    date_of_birth
    Date,
    language
    bigint
);