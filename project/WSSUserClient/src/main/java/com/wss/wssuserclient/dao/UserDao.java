package com.wss.wssuserclient.dao;

import com.wss.wssuserclient.model.User;

public interface UserDao {

    public int userLogin(String username, String password);

    public int addUser(User user);

    public User getUserByUsername(String username);

    public String getUserPassword(int id);

    public int updateUserPassword(String password, int id);
}
