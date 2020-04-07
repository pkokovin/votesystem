delete
from user_roles;
delete
from users;
alter
sequence global_seq restart with 100000;

insert into users (name, email, password)
values ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

insert into user_roles (role, user_id)
values ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001),
       ('ROLE_USER', 100001);

insert into restaurants (name)
values ('1rest'),
       ('2rest'),
       ('3rest'),
       ('4rest'),
       ('5rest');
insert into dishes (name, price, restaurant_id)
values  ('today-1st_dish_1rest', '30000', 100002),
        ('today-1st_dish_2rest', '30000', 100003),
        ('today-1st_dish_3rest', '30000', 100004),
        ('today-1st_dish_4rest', '30000', 100005),
        ('today-1st_dish_5rest', '30000', 100006),
        ('today-second_dish_1rest', '30000', 100002),
        ('today-second_dish_2rest', '30000', 100003),
        ('today-second_dish_3rest', '30000', 100004),
        ('today-second_dish_4rest', '30000', 100005),
        ('today-second_dish_5rest', '30000', 100006),
        ('today-third_dish_1rest', '30000', 100002),
        ('today-third_dish_2rest', '30000', 100003),
        ('today-third_dish_3rest', '30000', 100004),
        ('today-third_dish_4rest', '30000', 100005),
        ('today-third_dish_5rest', '30000', 100006),
        ('today-fourth_dish_1rest', '30000', 100002),
        ('today-fourth_dish_2rest', '30000', 100003),
        ('today-fourth_dish_3rest', '30000', 100004),
        ('today-fourth_dish_4rest', '30000', 100005),
        ('today-fourth_dish_5rest', '30000', 100006);

insert into dishes (name, price, restaurant_id, menu_date)
values ('1-0st_dish', '30000', 100002, '2020-02-25'),
       ('1-1st_dish', '30000', 100003, '2020-02-25'),
       ('1-2st_dish', '30000', 100004, '2020-02-26'),
       ('1-3st_dish', '30000', 100005, '2020-02-25'),
       ('1-4st_dish', '30000', 100006, '2020-02-26'),
       ('2-0st_dish', '30000', 100002, '2020-02-25'),
       ('2-1st_dish', '30000', 100003, '2020-02-25'),
       ('2-2st_dish', '30000', 100004, '2020-02-26'),
       ('2-3st_dish', '30000', 100005, '2020-02-25'),
       ('2-4st_dish', '30000', 100006, '2020-02-26'),
       ('3-0st_dish', '30000', 100002, '2020-02-25'),
       ('3-1st_dish', '30000', 100003, '2020-02-25'),
       ('3-2st_dish', '30000', 100004, '2020-02-26'),
       ('3-3st_dish', '30000', 100005, '2020-02-26'),
       ('3-4st_dish', '30000', 100006, '2020-02-26'),
       ('4-0st_dish', '30000', 100002, '2020-02-25'),
       ('4-1st_dish', '30000', 100003, '2020-02-25'),
       ('4-2st_dish', '30000', 100004, '2020-02-26'),
       ('4-3st_dish', '30000', 100005, '2020-02-25'),
       ('4-4st_dish', '30000', 100006, '2020-02-26'),
       ('5-0st_dish', '30000', 100002, '2020-02-25'),
       ('5-1st_dish', '30000', 100003, '2020-02-26'),
       ('5-2st_dish', '30000', 100004, '2020-02-25'),
       ('5-3st_dish', '30000', 100005, '2020-02-26'),
       ('5-4st_dish', '30000', 100006, '2020-02-26');

insert into user_votes (user_id, restaurant_id, vote_datetime)
values ('100000', '100002', '2020-02-25 10:00:00'),
       ('100000', '100005', '2020-02-26 10:00:00'),
       ('100001', '100003', '2020-02-25 10:00:00'),
       ('100001', '100006', '2020-02-26 10:00:00');