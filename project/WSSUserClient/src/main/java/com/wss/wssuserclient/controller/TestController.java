package com.wss.wssuserclient.controller;

import com.wss.wssuserclient.dao.ProductDao;
import com.wss.wssuserclient.dao.RecordDao;
import com.wss.wssuserclient.dao.UserDao;
import com.wss.wssuserclient.dao.WorkerDao;
import com.wss.wssuserclient.model.Product;
import com.wss.wssuserclient.model.Record;
import com.wss.wssuserclient.model.User;
import com.wss.wssuserclient.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@ComponentScan("com.wss.wssuserclient")
public class TestController {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RecordDao recordDao;
    @Autowired private WorkerDao workerDao;



}
