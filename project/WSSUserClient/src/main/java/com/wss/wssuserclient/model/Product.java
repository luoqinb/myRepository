package com.wss.wssuserclient.model;

//create table t_product
//        (
//        id int auto_increment,
//        price double not null,
//        pid varchar(50) not null comment 'Product Identifier',
//        constraint t_product_pk
//        primary key (id)
//        )
//        comment 'Product info';

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Product {
    private int id;
    private double price;
    private String pid;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", pid='" + pid + '\'' +
                '}';
    }
}
