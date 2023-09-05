create table driver (
id serial primary key,
name varchar,
age int,
license boolean
avto_id references avto(id)
)

create table avto (
id serial primary key,
marka varchar,
model varchar,
price int
)