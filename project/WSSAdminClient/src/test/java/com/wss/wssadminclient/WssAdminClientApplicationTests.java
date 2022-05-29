package com.wss.wssadminclient;

import com.wss.wssadminclient.dao.ProductDao;
import com.wss.wssadminclient.dao.RecordDao;
import com.wss.wssadminclient.dao.UserDao;
import com.wss.wssadminclient.dao.WorkerDao;
import com.wss.wssadminclient.mapper.RecordMapper;
import com.wss.wssadminclient.model.Product;
import com.wss.wssadminclient.model.Record;
import com.wss.wssadminclient.model.User;
import com.wss.wssadminclient.model.Worker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class WssAdminClientApplicationTests {
    @Autowired
    RecordDao recordDao;
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    UserDao userDao;
    @Autowired
    WorkerDao workerDao;
    @Autowired
    ProductDao productDao;
    @Test
    void contextLoads() {
    }

    @Test

    void getAllSalary() {
        List<Record> salaryList = recordDao.getAllSalary();
        for (Record o : salaryList) {
            System.out.println(o);
        }
    }

    @Test
    void getSalaryByWid(){
        List<Record> salaryList = recordDao.getAllSalaryByWid("A320");
        for (Record o : salaryList) {
            System.out.println(o);
        }
    }

    @Test
    void testGetSalary() {
        List<Worker> workerList = workerDao.getAllWorkers();
        for (Worker w : workerList) {
            System.out.println(w);
        }
    }

}
