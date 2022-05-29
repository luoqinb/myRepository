package com.wss.wssuserclient.model;

//create table t_record
//        (
//        id int auto_increment,
//        uid int not null comment 'User ID',
//        pid int not null comment 'Product ID',
//        price double not null comment 'Total price of this record',
//        status int not null comment '1 - Submitted, 2 - Permitted, 3 - Refused.',
//        constraint t_record_pk
//        primary key (id),
//        constraint t_record_t_product_id_fk
//        foreign key (pid) references t_product (id),
//        constraint t_record_t_worker_id_fk
//        foreign key (uid) references t_worker (id)
//        )
//        comment 'Submit/Salary Record';

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Record {
    private int id, uid, status, pid, amount;
    private double price;
    private String dateTime, productId;

    public Record(int uid, int status, int pid, int amount, double price, String dateTime) {
        this.uid = uid;
        this.status = status;
        this.pid = pid;
        this.amount = amount;
        this.price = price;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", uid=" + uid +
                ", status=" + status +
                ", pid=" + pid +
                ", amount=" + amount +
                ", price=" + price +
                ", dateTime='" + dateTime + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }
}
