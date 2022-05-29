package com.wss.wssadminclient.dao.impl;

import com.wss.wssadminclient.dao.UserDao;
import com.wss.wssadminclient.mapper.UserMapper;
import com.wss.wssadminclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper userMapper;

//    @Override
//    public int userLogin(String username, String password) {
//        if (userMapper.userLogin(username, password) == 0) {
//            return 0; // Because of no return (0) - wrong username or password
//        } else return 1; // Username and password checked, return login succeed
//
//    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user.getUsername(), user.getPassword());
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public int getUserIdByUsername(String username) {
        return userMapper.getUserIdByUsername(username);
    }

    @Override
    public int setUserRole3(int id) {
        return userMapper.setUserRole3(id);
    }
}
