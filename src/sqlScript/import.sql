/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  malek
 * Created: Apr 18, 2021
 */

/* create database shop;
 */
CREATE DATABASE IF NOT EXISTS shop;
create TABLE category(
    category_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(100)
   
);
create TABLE product(
    product_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(100),
    product_description text,
    product_category int,
    product_stock INTEGER,
    product_price float,
    product_img VARCHAR(255),
    foreign key (product_category) references category(category_id)  ON DELETE CASCADE  

);

create table user(
    user_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(100) UNIQUE,
    user_email VARCHAR(100) UNIQUE,
    user_password VARCHAR(255),
    user_type VARCHAR(100),
    user_phone VARCHAR(20),
    user_img  VARCHAR(255)

);
create table orders(
    orders_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id INT,
    orders_created_at TIMESTAMP default CURRENT_TIMESTAMP,
    foreign key (user_id) references user(user_id)  ON DELETE CASCADE
);
create table review(
    review_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    review_content text,
    review_created_at TIMESTAMP default CURRENT_TIMESTAMP
);
create table user_review_product(
    user_id INT,
    review_id INT,
    product_id INT,
    foreign key (user_id) references user(user_id)  ON DELETE CASCADE  ,
    foreign key (review_id) references review(review_id)  ON DELETE CASCADE  ,
    foreign key (product_id) references product(product_id)  ON DELETE CASCADE  ,
    primary key (user_id, review_id, product_id)
);

create table cart(
    cart_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
     user_id INT,
    product_id INT,
    quantity INT,
        orders_id INT,
    foreign key (orders_id) references orders(orders_id)  ON DELETE CASCADE  ,

    foreign key (user_id) references user(user_id)  ON DELETE CASCADE  ,
    foreign key (product_id) references product(product_id)  ON DELETE CASCADE

);