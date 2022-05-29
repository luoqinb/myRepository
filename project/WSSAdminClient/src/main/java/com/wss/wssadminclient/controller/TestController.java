package com.wss.wssadminclient.controller;

import com.wss.wssadminclient.dao.*;
import com.wss.wssadminclient.model.Product;
import com.wss.wssadminclient.model.User;
import com.wss.wssadminclient.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@CrossOrigin
public class TestController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private WorkerDao workerDao;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private RecordDao recordDao;
    @Autowired
    private ProductDao productDao;


    // get a product by pid
    @GetMapping("/api/getProduct/{pid}")
    @ResponseBody
    public Product getProduct(@PathVariable("pid") String pid) {
        return productDao.getProductByPid(pid);
    }

    // get all products
    @GetMapping("/api/getProducts")
    @ResponseBody
    public List<Product> productList() {
        return productDao.getAllProducts();
    }

    @GetMapping("/api/getWorker/{id}")
    @ResponseBody
    public Worker getWorkerById(@PathVariable("id") Integer id) {
        return workerDao.getWorkerById(id);
    }

    @PutMapping("/api/updateWorker/{id}")
    @ResponseBody
    public String updateWorkerById(@PathVariable("id") Integer id, HttpServletRequest req) {

        Worker worker = workerDao.getWorkerById(id);
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
//        worker.setGender(req.getParameter("gender"));
//        worker.setName(req.getParameter("name"));

        if (workerDao.updateWorkerById(name, gender, id) == 1) {
            return "success";
        } else return "failed";
    }
}