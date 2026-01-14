create table admin
(
    id       serial primary key,
    username varchar(50)  not null unique,
    password varchar(255) not null
);

create table product
(
    id    serial primary key,
    name  varchar(100)   not null,
    brand varchar(50)    not null,
    price decimal(12, 2) not null,
    stock int            not null
);

create table customer
(
    id      serial primary key,
    name    varchar(100) not null,
    phone   varchar(20),
    email   varchar(100) unique,
    address varchar(255)
);

create table invoice
(
    id           serial primary key,
    customer_id  int references customer (id),
    create_at    date default current_timestamp,
    total_amount decimal(12, 2) not null
);

create table invoice_details
(
    id         serial primary key,
    invoice_id int references invoice (id),
    product_id int references product (id),
    quantity   int            not null,
    unit_price decimal(12, 2) not null
);

--Đăng nhập/đăng ký
create procedure register_admin(user_in varchar, pass_in varchar)
    language plpgsql as
$$
begin
    insert into admin(username, password) values (user_in, pass_in);
end;
$$;

create or replace function login_admin(user_in varchar, pass_in varchar)
    returns integer as
$$
begin
    return (select count(*) from admin where username = user_in and password = pass_in);
end;
$$ language plpgsql;

--1. Quản lý sản phẩm
--thêm sản phẩm mới
create or replace procedure add_product(name_in varchar, brand_in varchar, price_in decimal, stock_in int)
    language plpgsql as
$$
begin
    insert into product (name, brand, price, stock)
    VALUES (name_in, brand_in, price_in, stock_in);
end;
$$;

--hiển thị toàn bộ sản phẩm
create function find_all_product()
    returns table
            (
                id    int,
                name  varchar(100),
                brand varchar(50),
                price numeric,
                stock int
            )
as
$$
begin
    return query
        select * from product;
end;
$$ language plpgsql;

--tìm sản phẩm theo id
create function find_product_by_id(id_in int)
    returns table
            (
                id    int,
                name  varchar(100),
                brand varchar(50),
                price numeric,
                stock int
            )
as
$$
begin
    return query
        select p.id, p.name, p.brand, p.price::numeric, p.stock
        from product p
        where p.id = id_in;
end;
$$ language plpgsql;

--cập nhật sản phẩm theo id
create procedure update_product(
    id_in int,
    name_in varchar(100),
    brand_in varchar(50),
    price_in numeric,
    stock_in int
)
    language plpgsql
as
$$
begin
    update product
    set name=name_in,
        brand=brand_in,
        price=price_in,
        stock=stock_in
    where id = id_in;
end;
$$;

--xóa sản phẩm theo id
create procedure delete_product(id_in int)
    language plpgsql
as
$$
begin
    delete from product where id = id_in;
end;
$$;

--tìm sp theo nhãn hàng
create function find_product_by_brand(brand_in varchar(50))
    returns table
            (
                id    int,
                name  varchar(100),
                brand varchar(50),
                price numeric,
                stock int
            )
as
$$
declare
    brand_search varchar(50);
begin
    brand_search := concat('%', brand_in, '%');
    return query
        select p.id, p.name, p.brand, p.price::numeric, p.stock
        from product p
        where p.brand ilike brand_search;
end;
$$
    language plpgsql;

--tìm kiếm sản phẩm theo khoảng giá
create function find_product_by_price(price_before numeric,
                                      price_after numeric)
    returns table
            (
                id    int,
                name  varchar(100),
                brand varchar(50),
                price numeric,
                stock int
            )
as
$$
begin
    return query
        select p.id, p.name, p.brand, p.price::numeric, p.stock
        from product p
        where p.price >= price_before
          and p.price <= price_after;
end;
$$ language plpgsql;

--tìm kiếm sản phẩm theo stock
create function find_product_by_stock(stock_in int)
    returns table
            (
                id    int,
                name  varchar(100),
                brand varchar(50),
                price numeric,
                stock int
            )
as
$$
begin
    return query
        select p.id, p.name, p.brand, p.price::numeric, p.stock
        from product p
        where p.stock <= stock_in;
end;
$$ language plpgsql;

--2Quản lý khách hàng
--Hiển thị danh sách khách hàng
create function find_all_customer()
    returns table
            (
                id      int,
                name    varchar(100),
                phone   varchar(20),
                email   varchar(100),
                address varchar(255)
            )
    language plpgsql
as
$$
begin
    return query
        select * from customer;
end;
$$;

--thêm khách hàng mới
create procedure add_customer(name_in varchar, phone_in varchar, email_in varchar, address_in varchar)
    language plpgsql as
$$
begin
    insert into customer (name, phone, email, address)
    values (name_in, phone_in, email_in, address_in);
end;
$$;

--tìm kiếm khách hàng theo id
create or replace function find_customer_by_id(id_in int)
    returns table
            (
                id      int,
                name    varchar(100),
                phone   varchar(20),
                email   varchar(100),
                address varchar(255)
            )
as
$$
begin
    return query
        select c.id, c.name, c.phone, c.email, c.address
        from customer c
        where c.id = id_in;
end;
$$ language plpgsql;

--cập nhật thông tin khách hàng
create procedure update_customer(
    id_in int,
    name_in varchar(100),
    phone_in varchar(20),
    email_in varchar(100),
    address_in varchar(255)
)
    language plpgsql
as
$$
begin
    update customer
    set name    = name_in,
        phone   = phone_in,
        email   = email_in,
        address = address_in
    where id = id_in;
end;
$$;

--xóa khách hàng
create procedure delete_customer(id_in int)
    language plpgsql
as
$$
begin
    delete from customer where id = id_in;
end;
$$;

--3. Quản lý hóa đơn
--hiển thị danh sách hóa đơn
create function find_all_invoice()
    returns table
            (
                id           int,
                customer_id  int,
                create_at    date,
                total_amount decimal(12, 2)
            )
as
$$
begin
    return query
        select * from invoice;
end;
$$ language plpgsql;

--thêm mới hóa đơn
create or replace procedure add_invoice(customer_id_in int, amount_in decimal(12,2))
    language plpgsql
as
$$
begin
    insert into invoice (customer_id, total_amount)
    values (customer_id_in, amount_in);
end;
$$;

--tìm kiếm hóa đơn theo tên khách hàng
create function find_invoice_by_name(name_in varchar(100))
    returns table
            (
                id            int,
                customer_id   int,
                customer_name varchar(100),
                create_at     date,
                total_amount  decimal(12, 2)
            )
as
$$
declare
    name_search varchar(100);
begin
    name_search := concat('%', name_in, '%');
    return query
        select i.id, i.customer_id, c.name, i.create_at, i.total_amount
        from invoice i
                 join customer c on c.id = i.customer_id
        where c.name ilike name_search;

end;
$$
    language plpgsql;

--tìm kiếm hóa đơn theo ngày/tháng/năm
create function find_invoice_by_date(date_in date)
    returns table
            (
                id           int,
                customer_id  int,
                create_at    date,
                total_amount decimal(12, 2)
            )
as
$$
begin
    return query
        select *
        from invoice i
        where i.create_at = date_in;
end;
$$ language plpgsql;

--4. Thống kê doanh thu
--theo ngày
create function get_revenue_by_date(date_in date)
    returns numeric(12, 2) as
$$
begin
    return (select coalesce(sum(quantity * unit_price), 0)
            from invoice_details id
                     join invoice i on i.id = id.invoice_id
            where i.create_at::date = date_in);
end;
$$ language plpgsql;

--theo tháng
create function get_revenue_by_month(month_in int, year_in int)
    returns numeric(12, 2) as
$$
begin
    return (select coalesce(sum(quantity * unit_price), 0)
            from invoice_details id
                     join invoice i on i.id = id.invoice_id
            where extract(month from i.create_at) = month_in
              and extract(year from i.create_at) = year_in);
end;
$$ language plpgsql;

--theo năm
create function get_revenue_by_year(year_in int)
    returns numeric(12, 2) as
$$
begin
    return (select coalesce(sum(quantity * unit_price), 0)
            from invoice_details id
                     join invoice i on i.id = id.invoice_id
            where extract(year from i.create_at) = year_in);
end;
$$ language plpgsql;