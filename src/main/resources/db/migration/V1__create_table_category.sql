create table category(
    id bigserial primary key,
    name varchar(100) not null unique
);