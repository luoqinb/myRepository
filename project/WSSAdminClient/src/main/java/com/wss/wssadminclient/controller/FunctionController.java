package com.wss.wssadminclient.controller;

import com.wss.wssadminclient.auth.PassCodeUtil;
import com.wss.wssadminclient.dao.*;
import com.wss.wssadminclient.model.Product;
import com.wss.wssadminclient.model.Record;
import com.wss.wssadminclient.model.User;
import com.wss.wssadminclient.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FunctionController {
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

    @GetMapping("/api/getSubmittedRecords")
    @ResponseBody
    public List<Record> getSubmittedRecords() {
        return recordDao.getSubmittedRecords();
    }

    @GetMapping("/api/getAcceptedRecords")
    @ResponseBody
    public List<Record> getAcceptedRecords() {
        return recordDao.getAcceptedRecords();
    }

    @GetMapping("/api/getRefusedRecords")
    @ResponseBody
    public List<Record> getRefusedRecords() {
        return recordDao.getRefusedRecords();
    }

    // Add new product
    @PostMapping("/api/addProduct")
    @ResponseBody
    public String addProduct(HttpServletRequest request) {
        String pid = request.getParameter("pid");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");

        Product product = new Product(price, pid, description);
        try {
            if (productDao.addProduct(product) == 1) {
                return "success";
            } else return "failed";
        } catch (DuplicateKeyException e) {
            return "duplicated";
        }
    }

    // Get record by Worker ID
    @GetMapping("/api/getSalaryById")
    @ResponseBody
    public List<Record> recordList(String wid) {
        Worker worker = workerDao.getWorker(wid);
        User user = userDao.getUserById(worker.getUid());
        return recordDao.getUserRecords(user.getId());
    }

    // Get all workers
    @GetMapping("/api/getWorkers")
    @ResponseBody
    public List<Worker> workerList() {
        return workerDao.getAllWorkers();
    }

    @PutMapping("/api/acceptRecord/{id}")
    @ResponseBody
    public String acceptRecord(HttpServletRequest request, @PathVariable("id") int id) {
        recordDao.acceptRecord(id);
        return "success";
    }

    @PutMapping("/api/declineRecord/{id}")
    @ResponseBody
    public String declineRecord(HttpServletRequest request, @PathVariable("id") int id) {
        recordDao.refuseRecord(id);
        return "success";
    }

    @PostMapping("/api/changePass")
    public String changePassAction(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws InterruptedException {
        System.out.println("Action received.");
        String oldPassword = request.getParameter("auth_password");
        String password = request.getParameter("auth_new_password");
        System.out.println("Old: " + oldPassword);
        System.out.println("New: " + password);

        if (oldPassword.equals(PassCodeUtil.decodePassword(adminDao.getAdminPassword()))) {
            adminDao.updateAdminPassword(PassCodeUtil.encodePassword(password));

            // perform logout
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
                auth.setAuthenticated(false);
            }
            return "redirect:/login?passwordChanged";

        } else {
            return "redirect:/changePassword?oldPassWrong";
        }
    }

    @PostMapping("/api/createNewEmp")
    @ResponseBody
    public String addNewEmp(HttpServletRequest request) {
        // 1. Create a worker - wid, name
        String wid = request.getParameter("wid");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        // 2. Create a User - username, password
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // Encrypt password
        password = PassCodeUtil.encodePassword(password);

        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        Worker worker = new Worker();
        worker.setWid(wid);
        worker.setName(name);
        worker.setGender(gender);

        System.out.println(user);
        System.out.println(worker);

        try {
            userDao.addUser(user);
            workerDao.addWorker(worker, userDao.getUserIdByUsername(user.getUsername()));
        } catch (DuplicateKeyException e) {
            return "duplicated";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

    // Resign a worker
    @PutMapping("/api/resignWorker")
    @ResponseBody
    public String resignWorker(HttpServletRequest request) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Worker worker = workerDao.getWorkerById(id);
        User user = userDao.getUserById(worker.getUid());
        if (workerDao.resignWorker(id) == 1 && userDao.setUserRole3(user.getId()) == 1) {
            return "success";
        } else return "failed";
    }

}
