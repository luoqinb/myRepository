package com.wss.wssadminclient.dao;

public interface AdminDao {
    public String getAdminPassword();

    public int updateAdminPassword(String password);
}
