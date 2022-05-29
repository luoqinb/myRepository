package com.wss.wssadminclient.model;

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

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Product {
    private int id;
    private double price;
    private String pid, description;

    public Product(double price, String pid, String description) {
        this.price = price;
        this.pid = pid;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", pid='" + pid + '\'' +
                '}';
    }

}
