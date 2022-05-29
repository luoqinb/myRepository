package com.wss.wssadminclient.dao.impl;


import com.wss.wssadminclient.dao.AdminDao;
import com.wss.wssadminclient.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminDao")
public class AdminDaoImpl implements AdminDao {

    @Autowired private AdminMapper adminMapper;

    @Override
    public String getAdminPassword() {
        return adminMapper.getAdminPassword();
    }

    @Override
    public int updateAdminPassword(String password) {
        return adminMapper.updateAdminPassword(password);
    }

}
