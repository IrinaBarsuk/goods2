create table manufacturer
(
    id                int NOT NULL primary key AUTO_INCREMENT,
    name_manufacturer varchar(100),
    country           varchar(100),
    city              varchar(100),
    address           varchar(100),
    phone_number      varchar(100),
    email             varchar(100)
);

create table category
(
    id            int NOT NULL primary key AUTO_INCREMENT,
    name_category varchar(100)
);

create table type_metal
(
    id         int NOT NULL primary key AUTO_INCREMENT,
    type_metal varchar(100)
);

create table users
(
    id       int NOT NULL primary key AUTO_INCREMENT,
    username varchar(100),
    password varchar(100),
    enabled  int
);

create table roles
(
    id   int NOT NULL primary key AUTO_INCREMENT,
    name varchar(100)
);

create table users_roles
(
    user_id int,
    role_id int
);

create table goods
(
    id                 int NOT NULL primary key AUTO_INCREMENT,
    fk_category_id     int,
    fk_manufacturer_id int,
    vendor_code        varchar(100),
    name_goods         varchar(100),
    fk_type_metal_id   int,
    price              float,
    description        varchar(100),
    image              blob default NULL,
    foreign key (fk_category_id) references category (id),
    foreign key (fk_manufacturer_id) references manufacturer (id),
    foreign key (fk_type_metal_id) references type_metal (id)
);