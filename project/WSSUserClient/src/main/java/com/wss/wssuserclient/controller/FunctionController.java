package com.wss.wssuserclient.controller;

import com.wss.wssuserclient.auth.PassCodeUtil;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class FunctionController {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RecordDao recordDao;


    @PostMapping("/api/submitRecord")
    @ResponseBody
    public String submitRecord(HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user.getRole() == 3) {
            return "denied";
        }
        System.out.println(user);
        int uid = user.getId();
        String pid = request.getParameter("pid");

        Product product = productDao.getProductByPid(pid);
        double price = Double.parseDouble(request.getParameter("totalPrice"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        String dateTime = request.getParameter("dateTime");

        Record record = new Record();
        record.setAmount(amount);
        record.setDateTime(dateTime);
        record.setPid(product.getId());
        record.setUid(uid);
        record.setPrice(price);
        record.setStatus(1);

        System.out.println(record);

        try {
            recordDao.submitNewRecord(record);
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }

        return "success";
    }

    @PostMapping("/api/changePass")
    public String changePassAction(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws InterruptedException {
        User user = (User) session.getAttribute("loginUser");
        String userPassword = user.getPassword();
        System.out.println("Action received.");
        String oldPassword = request.getParameter("auth_password");
        String password = request.getParameter("auth_new_password");
        System.out.println("Old: " + oldPassword);
        System.out.println("New: " + password);

        if (oldPassword.equals(PassCodeUtil.decodePassword(userPassword))) {
            userDao.updateUserPassword(PassCodeUtil.encodePassword(password), user.getId());
            System.out.println("password changed.");

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

    // get product by pid
    @GetMapping("/api/getProductByPid/{pid}")
    @ResponseBody
    public Product getProductByPid(@PathVariable("pid") String pid) {
        return productDao.getProductByPid(pid);
    }


}
