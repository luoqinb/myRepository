package com.wss.wssuserclient.auth;

import com.wss.wssuserclient.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("demoUserDetailService")
public class DemoUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.wss.wssuserclient.model.User user = userDao.getUserByUsername(username);
        System.out.println("username: "+username);
        if (user == null) {
            throw new UsernameNotFoundException(username + "not found.");
        }
        System.out.println("User: " + user);

        UserDetails userDetails = User.builder()
                .username(user.getUsername())
                .password("{noop}"+PassCodeUtil.decodePassword(user.getPassword()))
                .roles("USER")
                .build();
        return userDetails;
    }
}
