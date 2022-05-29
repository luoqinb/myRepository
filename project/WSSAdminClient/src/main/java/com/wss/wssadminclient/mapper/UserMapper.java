package com.wss.wssadminclient.mapper;

import com.wss.wssadminclient.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    // Add a new worker user (because of adding a new worker)
    @Insert("insert into t_user (username, password, role) values (#{username}, #{password}, 1);")
    public int addUser(String username, String password);

    // Get user by id
    @Select("select * from t_user where id=#{id};")
    public User getUserById(int id);

    // Get user id by username
    @Select("select id from t_user where username=#{username};")
    public int getUserIdByUsername(String username);

    // Resign worker - set user role to 3
    @Update("update t_user set role=3 where id=#{id}")
    public int setUserRole3(int id);



}
