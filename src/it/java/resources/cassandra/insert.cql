CREATE
KEYSPACE IF NOT EXISTS test
    WITH replication = {
        'class' : 'SimpleStrategy',
        'replication_factor' : 1
        };
use test;

CREATE table IF NOT EXISTS gebruiker
(
    id
    int
    primary key,
    voornaam   varchar,
    achternaam varchar,
    leeftijd   int,
    titel      varchar,
    is_admin   boolean
)