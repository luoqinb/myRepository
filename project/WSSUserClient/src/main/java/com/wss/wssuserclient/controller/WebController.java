package com.wss.wssuserclient.controller;

import com.wss.wssuserclient.dao.ProductDao;
import com.wss.wssuserclient.dao.RecordDao;
import com.wss.wssuserclient.dao.UserDao;
import com.wss.wssuserclient.model.Product;
import com.wss.wssuserclient.model.Record;
import com.wss.wssuserclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RecordDao recordDao;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String select(Model model, HttpSession session, Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());
        session.setAttribute("loginUser", user);
        System.out.println(user);
        List<Product> products = productDao.getAllProducts();
        model.addAttribute("products", products);
        System.out.println(products);
        return "base";
    }

    @GetMapping("/changePassword")
    public String changePassword(HttpSession session, Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());
        session.setAttribute("loginUser", user);
        return "changepasswd";
    }

    @GetMapping("/login")
    public String login() {
        return "auth";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            auth.setAuthenticated(false);
        }
        return "redirect:login?logout";
    }


    @GetMapping("/submitStatus")
    public String submitStatus(HttpSession session, Principal principal, Model model) {
        User user = userDao.getUserByUsername(principal.getName());
        session.setAttribute("loginUser", user);

        List<Record> oldRecordList = recordDao.getRecordsByUid(user.getId());
        List<Record> recordList = new ArrayList<>();
        for (Record record : oldRecordList) {
            String productId = productDao.getProductById(record.getPid()).getPid();
            record.setProductId(productId);
            recordList.add(record);
        }
        Collections.reverse(recordList);
        System.out.println(recordList);

        model.addAttribute("recordList", recordList);

        return "submit";
    }

    @GetMapping("/salary")
    public String salary(HttpSession session, Principal principal, Model model, @RequestParam(name = "year", required = false) Integer year, @RequestParam(name = "month", required = false) Integer month) {
        User user = userDao.getUserByUsername(principal.getName());
        session.setAttribute("loginUser", user);
        try {
            if (year != null && month != null) {
                String yearMonth = year + "-" + month;
                System.out.println("yearMonth: " + yearMonth);
                double salaryOfYearMonth = recordDao.salaryOfYearMonth(yearMonth);
                System.out.println("Salary: " + salaryOfYearMonth);
                List<Record> oldRecordList = recordDao.getRecordsByYearMonth(yearMonth);
                List<Record> recordList = new ArrayList<>();
                for (Record record : oldRecordList) {
                    String productId = productDao.getProductById(record.getPid()).getPid();
                    record.setProductId(productId);
                    recordList.add(record);
                }
                Collections.reverse(recordList);
                System.out.println(recordList);

                model.addAttribute("salaryMsg", year + " 年 " + month + " 月的总工资（不含审核未通过记录）为 " + salaryOfYearMonth + "。");
                model.addAttribute("recordList", recordList);
            } else {
                List<Record> oldRecordList = recordDao.getRecordsByUid(user.getId());
                List<Record> recordList = new ArrayList<>();
                for (Record record : oldRecordList) {
                    String productId = productDao.getProductById(record.getPid()).getPid();
                    record.setProductId(productId);
                    recordList.add(record);
                }
                Collections.reverse(recordList);
                System.out.println(recordList);

                model.addAttribute("recordList", recordList);
            }
        } catch (NullPointerException e) {
            List<Record> recordList = new ArrayList<>();
            model.addAttribute("recordList", recordList);
        }
        return "salary";
    }
}
