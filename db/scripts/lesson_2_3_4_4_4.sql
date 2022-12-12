create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

start transaction;
set transaction isolation level repeatable read;
insert into products (name, producer, count, price) VALUES ('product_6', 'producer_6', 10, 115);
savepoint savepoint1;
insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 4, 90);
savepoint savepoint2;
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
savepoint savepoint3;
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 3, 40);
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
DROP TABLE products;
rollback to savepoint3;
select * from products;
rollback to savepoint2;
select * from products;
rollback to savepoint1;
select * from products;
commit;
