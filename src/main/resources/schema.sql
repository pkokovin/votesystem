drop table if exists user_roles cascade;
drop table if exists users cascade;
drop sequence if exists global_seq;

create sequence global_seq start with 100000;

create table users
(
    id                      integer default global_seq.nextval      primary key,
    name                    varchar                                 not null,
    email                   varchar                                 not null,
    password                varchar                                 not null,
    registered              timestamp default now()                 not null,
    enabled                 bool default true                       not null
);
create unique index users_unique_email_idx on users (email);

create table user_roles
(
    user_id                 integer                                 not null,
    role                    varchar,
    constraint user_roles_idx unique (user_id, role),
    foreign key (user_id) references users (id) on delete cascade
);

create table restaurants
(
    id                      integer default global_seq.nextval      primary key,
    name                    varchar                                 not null
);

create table dishes
(
    id                      integer default global_seq.nextval      primary key,
    name                    varchar                                 not null,
    price                   bigint                                  not null,
    restaurant_id           integer                                 not null,
    menu_date               date      default now()                 not null,
    constraint dishes_idx unique (name, restaurant_id, menu_date),
    foreign key (restaurant_id) references restaurants (id) on delete cascade
);

create table user_votes
(
    id                      integer default global_seq.nextval      primary key,
    user_id                 integer                                 not null,
    restaurant_id           integer                                 not null,
    vote_datetime           timestamp default now()                 not null,
    constraint user_votes unique (user_id, restaurant_id, vote_datetime),
    foreign key (user_id) references users (id) on delete cascade,
    foreign key (restaurant_id) references restaurants (id) on delete cascade
)