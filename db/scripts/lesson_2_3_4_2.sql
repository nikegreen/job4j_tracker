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

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

call insert_data('product_2', 'producer_2', 15, 32);

create or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count > 0 THEN
            update products set count = count - u_count where id = u_id;
        end if;
        if tax > 0 THEN
            update products set price = price + price * tax;
        end if;
    END;
$$;

call update_data(10, 0, 1);

call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_3', 'producer_3', 8, 115);

call update_data(0, 0.2, 0);

create or replace procedure delete_data(i_id integer)
language 'plpgsql'
as $$
    BEGIN
        delete from products
        where id = i_id;
    END
$$;

call delete_data(1);

delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

create or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

select f_insert_data('product_1', 'producer_1', 25, 50);

create or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count > 0 THEN
            update products set count = count - u_count where id = u_id;
            select into result count from products where id = u_id;
        end if;
        if tax > 0 THEN
            update products set price = price + price * tax;
            select into result sum(price) from products;
        end if;
        return result;
    end;
$$;

select f_update_data(10, 0, 1);

select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 8, 115);

select f_update_data(0, 0.2, 0);

create or replace function f_delete_zero_data()
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
		select into result count(*) from products where price = 0;
        delete from products where price = 0;
        return result;
    end;
$$;

select f_insert_data('product_4', 'producer_4', 8, 0);
select f_insert_data('product_5', 'producer_5', 8, 0);

select f_delete_zero_data();
