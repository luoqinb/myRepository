package com.wss.wssadminclient.dao;

import com.wss.wssadminclient.model.User;

public interface UserDao {

//    public int userLogin(String username, String password);

    public int addUser(User user);

    public User getUserById(int id);

    public int getUserIdByUsername(String username);

    public int setUserRole3(int id);
}
