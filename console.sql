create database ss15;

use ss15;

# drop database ss15;

create table user
(
    id_user  int primary key auto_increment,
    username varchar(50) not null unique
);

insert into user(username)
values ('Phạm Trung Hiếu');

create table Product
(
    id          int primary key auto_increment,
    name        varchar(255),
    description text,
    price       double
);

insert into Product(name, description, price)
values ('iPhone 15', 'iPhone 15 với thiết kế hiện đại và chip A16 Bionic', 999.0),
       ('Samsung S24', 'Samsung S24 màn hình AMOLED 120Hz, camera siêu nét', 899.0),
       ('Xiaomi 13', 'Xiaomi 13 cấu hình mạnh, giá hợp lý', 699.0);

create table Review
(
    id         int primary key auto_increment,
    id_product int,
    id_user    int,
    rating     int check (rating between 1 and 5),
    comment    text,
    foreign key (id_product) references product (id)
);

create table Cart
(
    id         int primary key auto_increment,
    id_user    int,
    id_product int,
    quantity   int,
    foreign key (id_product) references product (id)
);

create table Orders
(
    order_id       int primary key auto_increment,
    id_user        int,
    recipient_name varchar(100),
    address        text,
    phone_number   varchar(20),
    order_date     datetime default CURRENT_TIMESTAMP
);

create table Order_detail
(
    id            int primary key auto_increment,
    order_id      int,
    product_id    int,
    quantity      int,
    current_price double,
    foreign key (order_id) references orders (order_id),
    foreign key (product_id) references product (id)
);

delimiter //
create procedure find_all_cart()
begin
    select * from Cart;
end;

create procedure find_cart_by_user_id(
    id_user_in int
)
begin
    select * from Cart where id_user = id_user_in;
end;

create procedure create_cart(
    id_user_in int,
    id_product_in int,
    quantity_in int
)
begin
    insert into Cart(id_user, id_product, quantity)
    values (id_user_in, id_product_in, quantity_in);
end;

create procedure update_cart(
    id_in int,
    id_user_in int,
    id_product_in int,
    quantity_in int
)
begin
    update Cart
    set id_user    = id_user_in,
        id_product = id_product_in,
        quantity   = quantity_in
    where id = id_in;
end;

create procedure delete_cart(
    id_in int
)
begin
    delete from Cart where id = id_in;
end;

create procedure find_all_product()
begin
    select * from Product;
end;

create procedure find_product_by_id(
    id_in int
)
begin
    select * from Product where id = id_in;
end;

create procedure create_product(
    name_in varchar(255),
    description_in text,
    price_in double
)
begin
    insert into Product(name, description, price)
    values (name_in, description_in, price_in);
end;

create procedure find_all_review()
begin
    select * from Review;
end;

create procedure create_review(
    id_product_in int,
    id_user_in int,
    rating_in int,
    comment_in text
)
begin
    insert into Review(id_product, id_user, rating, comment)
    values (id_product_in, id_user_in, rating_in, comment_in);
end;

create procedure create_order(
    id_user_in int,
    recipient_name_in varchar(100),
    address_in text,
    phone_number_in varchar(20)
)
begin
    insert into Orders(id_user, recipient_name, address, phone_number)
    values (id_user_in, recipient_name_in, address_in, phone_number_in);
end;

create procedure get_orders_by_user_id(
    id_user_in int
)
begin
    select order_id, id_user, recipient_name, address, phone_number, order_date
    from Orders
    where id_user = id_user_in
    order by order_date desc;
end;

create procedure get_order_by_id(
    order_id_in int
)
begin
    select order_id, id_user, recipient_name, address, phone_number, order_date
    from Orders
    where order_id = order_id_in;
end;

create procedure create_order_detail(
    order_id_in int,
    product_id_in int,
    quantity_in int,
    current_price_in double
)
begin
    insert into Order_detail(order_id, product_id, quantity, current_price)
    values (order_id_in, product_id_in, quantity_in, current_price_in);
end;

create procedure get_order_details_by_order_id(
    order_id_in int
)
begin
    select od.id, od.order_id, od.product_id, od.quantity, od.current_price
    from Order_detail od
             join Product p on p.id = od.product_id
    where od.order_id = order_id_in;
end;
delimiter //