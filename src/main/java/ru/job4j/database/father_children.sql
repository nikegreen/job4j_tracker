/**
* many-to-one
*/
create table father(
    id serial primary key,
    name varchar(60)
);

create table children(
    id serial primary key,
    name varchar(60),
    father_id int references father(id) unique
);

insert into father(name) values ('Ivan');
insert into father(name) values ('Serg');

insert into children(name) values ('Petr', 1);
insert into children(name) values ('Stas', 1);
insert into children(name) values ('Oleg', 2);
insert into children(name) values ('Misha', 2);

select * from father;

select * from children where id in (select id from father);

