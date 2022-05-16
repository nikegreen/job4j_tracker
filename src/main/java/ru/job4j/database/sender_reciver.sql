/**
* many-to-many
*/
create table sender(
    id serial primary key,
    name varchar(60)
);

create table receiver(
    id serial primary key,
    name varchar(60)
);

create table message(
    id serial primary key,
    sender_id int references sender(id),
    receiver_id int references receiver(id)
);

insert into sender(name) values ('Ivan');
insert into sender(name) values ('Petr');

insert into receiver(name) values ('Stas');
insert into receiver(name) values ('Oleg');

insert into message(sender_id, receiver_id) values  (1, 1);
insert into message(sender_id, receiver_id) values  (1, 2);
insert into message(sender_id, receiver_id) values  (2, 1);
insert into message(sender_id, receiver_id) values  (2, 2);

select * from sender;
select * from receiver;
select * from message;
