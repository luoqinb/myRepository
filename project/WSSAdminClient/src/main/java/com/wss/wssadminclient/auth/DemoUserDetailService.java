package com.wss.wssadminclient.auth;

import com.wss.wssadminclient.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DemoUserDetailService implements UserDetailsService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = User.builder()
                .username("root")
                .password("{noop}"+PassCodeUtil.decodePassword(adminDao.getAdminPassword()))
                .roles("USER")
                .build();
        return userDetails;
    }
}
