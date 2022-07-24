create table if not exists country
(
    id      bigserial
    primary key,
    capital varchar(255),
    name    varchar(255)
    );

create table if not exists author
(
    id            bigserial
    primary key,
    date_of_birth timestamp,
    first_name    varchar(255),
    last_name     varchar(255),
    origin_id     bigint
    constraint fklm24wiq3ryog4l0uipj70btru
    references country,
    residence_id  bigint
    constraint fk5lmqi4t8eq5mjn7ijydygkw7o
    references country
    );

create table if not exists gebruiker
(
    id         bigserial
    primary key,
    achternaam varchar(255),
    is_admin   boolean,
    leeftijd   integer,
    titel      varchar(255),
    voornaam   varchar(255)
    );

create table if not exists genre
(
    id   bigserial
    primary key,
    name varchar(255)
    );


create table if not exists language
(
    id   bigserial
    primary key,
    name varchar(255)
    );


create table if not exists author_language
(
    author_id   bigint not null
    constraint fkdh45can9hv3pnbr6cdxipeba8
    references author,
    language_id bigint not null
    constraint fkf230gpard5b25t714t1vgq85g
    references language,
    primary key (author_id, language_id)
    );


create table if not exists book
(
    id          bigserial
    primary key,
    title       varchar(255),
    author_id   bigint
    constraint fkklnrv3weler2ftkweewlky958
    references author,
    language_id bigint
    constraint fkmrhfp9wfi5dy4bwl87bx8ivua
    references language
    );

create table if not exists book_genre
(
    book_id  bigint not null
    constraint fk52evq6pdc5ypanf41bij5u218
    references book,
    genre_id bigint not null
    constraint fk8l6ops8exmjrlr89hmfow4mmo
    references genre,
    primary key (book_id, genre_id)
    );