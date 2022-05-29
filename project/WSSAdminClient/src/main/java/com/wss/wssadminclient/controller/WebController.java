package com.wss.wssadminclient.controller;

import com.wss.wssadminclient.dao.*;
import com.wss.wssadminclient.model.Product;
import com.wss.wssadminclient.model.Record;
import com.wss.wssadminclient.model.User;
import com.wss.wssadminclient.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

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

    @GetMapping("/login")
    public String login() {
        return "auth";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/changePass")
    public String changePass() {
        return "changePass";
    }

    @GetMapping("/home")
    public String adminHome() {
        return "adminHome";
    }

    @GetMapping("/top")
    public String top() {
        return "Top";
    }

    @GetMapping("/left")
    public String left() {
        return "left";
    }

    @GetMapping("/GoodsManagement")
    public String GoodsManagement(Model model) {

        List<Product> productList = productDao.getAllProducts();
        model.addAttribute("productList", productList);

        return "GoodsManagement";
    }

    @GetMapping("/SubmissionStatus")
    public String SubmissionStatus(Model model) {
        // Get id, uid, pid, price, status, amount, datetime
        List<Record> submittedRecords = new ArrayList<>();
        List<Record> acceptedRecords = new ArrayList<>();
        List<Record> refusedRecords = new ArrayList<>();
        List<Record> oldSubmittedRecords = recordDao.getSubmittedRecords();
        List<Record> oldAcceptedRecords = recordDao.getAcceptedRecords();
        List<Record> oldRefusedRecords = recordDao.getRefusedRecords();
        for (Record record : oldSubmittedRecords) {
            User user = userDao.getUserById(record.getUid());
            Worker worker = workerDao.getWorkerByUid(user.getId());
            Product product = productDao.getProductById(record.getPid());
            record.setProductId(product.getPid());
            record.setUid(user.getId());
            record.setWid(worker.getWid());
            record.setWname(worker.getName());
            submittedRecords.add(record);
        }

        for (Record record : oldAcceptedRecords) {
            User user = userDao.getUserById(record.getUid());
            Worker worker = workerDao.getWorkerByUid(user.getId());
            Product product = productDao.getProductById(record.getPid());
            record.setProductId(product.getPid());
            record.setUid(user.getId());
            record.setWid(worker.getWid());
            record.setWname(worker.getName());
            acceptedRecords.add(record);
        }

        for (Record record : oldRefusedRecords) {
            User user = userDao.getUserById(record.getUid());
            Worker worker = workerDao.getWorkerByUid(user.getId());
            Product product = productDao.getProductById(record.getPid());
            record.setProductId(product.getPid());
            record.setUid(user.getId());
            record.setWid(worker.getWid());
            record.setWname(worker.getName());
            refusedRecords.add(record);
        }

        model.addAttribute("submittedRecords", submittedRecords);
        model.addAttribute("acceptedRecords", acceptedRecords);
        model.addAttribute("refusedRecords", refusedRecords);

        return "SubmissionStatus";
    }

    @GetMapping("/changePassword")
    public String changePassword(HttpSession session, Principal principal) {
        return "ChangePasswd";
    }

    @GetMapping("/EmployeeSalary")
    public String EmployeeSalary(Model model, @RequestParam(name = "wid", required = false) String wid, @RequestParam(name = "year", required = false) Integer year, @RequestParam(name = "month", required = false) Integer month) {

        if (wid == null && year == null && month == null) {
            List<Record> salaryList = recordDao.getAllSalary();
            model.addAttribute("salaryList", salaryList);
        } else if (wid != null && year == null && month == null) {
            List<Record> salaryList = recordDao.getAllSalaryByWid(wid);
            model.addAttribute("salaryList", salaryList);
            model.addAttribute("msg", "工号 " + wid + " 的查询结果：");
        } else if (wid == null && year != null && month != null) {
            String yearMonth = year + "-" + month;
            List<Record> salaryList = recordDao.getSalaryByYearMonth(yearMonth);
            model.addAttribute("salaryList", salaryList);
            model.addAttribute("msg", year + " 年 " + month + " 月的查询结果：");
        } else if (wid != null && year != null && month != null) {
            String yearMonth = year + "-" + month;
            List<Record> salaryList = recordDao.getSalaryByBoth(wid, yearMonth);
            model.addAttribute("salaryList", salaryList);
            model.addAttribute("msg", "工号 " + wid + " 在 " + year + " 年 " + month + " 月的查询结果：");
        } else {
            return "redirect:/EmployeeSalary";
        }

        return "EmployeeSalary";
    }

    @GetMapping("/EmployeeInformation")
    public String EmployeeInformation(Model model) {
        List<Worker> workerList = workerDao.getAllWorkers();
        model.addAttribute("workerList", workerList);
        return "EmployeeInformation";
    }
}
