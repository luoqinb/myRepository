package com.wss.wssuserclient.dao.daoimpl;

import com.wss.wssuserclient.dao.UserDao;
import com.wss.wssuserclient.mapper.UserMapper;
import com.wss.wssuserclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int userLogin(String username, String password) {
        if (userMapper.userLogin(username, password) == 0) {
            return 0; // Because of no return (0) - wrong username or password
        } else return 1; // Username and password checked, return login succeed

    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user.getUsername(), user.getPassword());
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public String getUserPassword(int id) {
        return userMapper.getUserPassword(id);
    }

    @Override
    public int updateUserPassword(String password, int id) {
        return userMapper.updateUserPassword(password, id);
    }

}
