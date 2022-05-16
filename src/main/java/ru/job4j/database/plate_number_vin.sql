/**
* one-to-one
*/
create table plate_number(
    id serial primary key,
    numb varchar(10)
);

create table vin(
    id serial primary key,
    numb varchar(30)
);

create table plate_number_vin(
    id serial primary key,
    plate_number_id int references plate_number(id) unique,
    vin_id int references vin(id) unique
);

insert into plate_number(numb) values ('x001xx');
insert into vin(numb) values ('46475859970');
insert into plate_number_vin(plate_number_id, vin_id) values  (1, 1);

select * from plate_number;
select * from vin;
select * from plate_number_vin;
