
create table Customers (
    id int primary key,
    name varchar(50),
    phone varchar(20),
    email varchar(50)
);

create table RoomType (
    id int primary key,
    name varchar(48),
    price decimal(10,2),
    capacity int not null
);

create type room_status as enum ('Available', 'Booked', 'Maintenance', 'Dirty');
create table Rooms (
    id int primary key,
    room_number varchar(10) unique not null,
    status room_status default 'Available',
    room_type_id int,
    constraint fk_room_type_id foreign key (room_type_id) references RoomType(id)
);

create type reservation_status as enum ('Pending', 'Paid', 'Cancelled');

create table Reservation (
    id int primary key,
    customer_id int,
    room_id int,
    check_in_date date not null,
    check_out_date date not null,
    total_price decimal(10,2),
    status reservation_status default 'Pending',
    create_at timestamp default current_timestamp,

    constraint fk_reservation_customer foreign key (customer_id) references Customers(id),
    constraint fk_reservation_room foreign key (room_id) references Rooms(id)
);

create type payment_status as enum ('Unpaid', 'Paid');

create table Payment (
    id int primary key,
    reservation_id int,
    payment_method varchar(30),
    amount decimal(10,2) not null,
    payment_date timestamp default current_timestamp,
    status payment_status default 'Unpaid',

    constraint fk_payment_reservation foreign key (reservation_id) references Reservation(id)
);

-- trigger 1: tự động tính tổng tiền đặt phòng (total_price)
create or replace function calculate_total_price_func()
returns trigger as $$
declare
v_price_per_night decimal(10,2);
    v_days int;
begin
    select rt.price into v_price_per_night
    from Rooms r
             join RoomType rt on r.room_type_id = rt.id
    where r.id = new.room_id;

    v_days := new.check_out_date - new.check_in_date;

    if v_days <= 0 then
            v_days := 1;
    end if;

    new.total_price := v_price_per_night * v_days;

return new;
end;
$$ language plpgsql;

create trigger trg_calculate_total_price
before insert on Reservation
for each row
execute function calculate_total_price_func();


-- trigger 2: tự động đổi trạng thái phòng sau khi đặt (booked)
create or replace function update_room_status_booked_func()
returns trigger as $$
begin
    update Rooms
    set status = 'Booked'
    where id = new.room_id;
return new;
end;
$$ language plpgsql;

create trigger trg_update_room_status_booked
after insert on Reservation
for each row
execute function update_room_status_booked_func();


-- trigger 3: tự động hoàn tất đơn đặt phòng và giải phóng phòng khi thanh toán xong
create or replace function update_reservation_after_or_update_payment_func()
returns trigger as $$
begin
    if new.status = 'Paid' then

    update Reservation
    set status = 'Paid'
    where id = new.reservation_id;

    update Rooms
    set status = 'Available'
    where id = (select room_id from Reservation where id = new.reservation_id);

end if;

return new;
end;
$$ language plpgsql;


create trigger trg_update_reservation_after_or_update_payment
after insert or update on Payment
for each row
execute function update_reservation_after_or_update_payment_func();


-- chèn dữ liệu ban đầu (seed data)
insert into Customers (id, name, phone, email) values
    (1, 'Warde Hatfield', '883-183-3420', 'whatfield0@cafepress.com'),
    (2, 'Lyndy Edmonson', '864-201-6705', 'ledmonson1@ustream.tv'),
    (3, 'Heloise Delos', '117-445-3497', 'hdelos2@friendfeed.com'),
    (4, 'Sandye Hamon', '511-717-5548', 'shamon3@ed.gov'),
    (5, 'Margy Feek', '597-645-6302', 'mfeek4@homestead.com');


insert into RoomType (id, name, price, capacity) values
    (1, 'Standard Single Room', 49.99, 1),
    (2, 'Standard Double Room', 74.49, 2),
    (3, 'VIP Family Room', 122.99, 3),
    (4, 'President Suite', 252.49, 4);


insert into Rooms (id, room_number, room_type_id) values
    (1, 'A101', 1),
    (2, 'A102', 1),
    (3, 'A103', 2),
    (4, 'A104', 4),
    (5, 'A105', 3),
    (6, 'A201', 2);


