create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);


create or replace function statement_tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger statement_tax_trigger
    after insert
    on products
    for each statement
    execute procedure statement_tax();



create or replace function row_tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger row_tax_trigger
    before insert
    on products
    for each row
    execute procedure row_tax();


CREATE OR REPLACE FUNCTION log_history_of_price()
  RETURNS TRIGGER AS
$$
BEGIN
    INSERT INTO history_of_price(name, price, date)
    VALUES(new.name, new.price, now());
	RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER last_name_changes
  BEFORE INSERT
  ON products
  FOR EACH ROW
  EXECUTE PROCEDURE log_history_of_price();


insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

