package com.wss.wssadminclient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
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
