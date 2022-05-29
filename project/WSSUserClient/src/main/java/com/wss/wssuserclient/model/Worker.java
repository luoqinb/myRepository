package com.wss.wssuserclient.model;

//
//create table t_worker
//        (
//        id int auto_increment,
//        name varchar(30) not null,
//        wid varchar(50) not null comment 'Worker ID',
//        constraint t_worker_pk
//        primary key (id)
//        )
//        comment 'Worker info';


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Worker {
    private int id, status, uid;
    private String name, wid, gender;

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", status=" + status +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", wid='" + wid + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public Worker(int uid, String name, String wid, String gender) {
        this.uid = uid;
        this.name = name;
        this.wid = wid;
        this.gender = gender;
    }
}
