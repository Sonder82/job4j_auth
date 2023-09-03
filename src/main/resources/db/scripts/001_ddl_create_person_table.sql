CREATE TABLE person (
id SERIAL PRIMARY KEY not null,
login varchar (2000) unique not null,
password varchar(2000) not null
);