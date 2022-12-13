CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);
insert into customers(first_name, last_name, age, country) values ('Din', 'Rid', 100, 'Germany');
insert into customers(first_name, last_name, age, country) values ('Alla', 'Pugacheva', 80, 'Israil');
insert into customers(first_name, last_name, age, country) values ('Mickle', 'Jekson', 70, 'USA');
insert into customers(first_name, last_name, age, country) values ('Ringo', 'Star', 70, 'UK');

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders(amount, customer_id) values(1000, 2);
insert into orders(amount, customer_id) values(3000, 3);
insert into orders(amount, customer_id) values(800, 2);
insert into orders(amount, customer_id) values(1500, 4);
insert into orders(amount, customer_id) values(600, 3);





select * from customers c
where c.age = (select min(age) from customers);


SELECT *
FROM customers WHERE customers.id NOT IN (SELECT orders.customer_id FROM orders);
