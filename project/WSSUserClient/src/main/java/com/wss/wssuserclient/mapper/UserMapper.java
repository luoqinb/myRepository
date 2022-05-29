package com.wss.wssuserclient.mapper;

import com.wss.wssuserclient.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    // User login
    @Select("select * from t_user where id=#{id} and password=#{password};")
    public int userLogin(String id, String password);

    // Add a new worker user (because of adding a new worker)
    @Insert("insert into t_user (username, password, role) values (#{username}, #{password}, 1);")
    public int addUser(String username, String password);

    // Get a user by username
    @Select("select * from t_user where username=#{username};")
    public User getUserByUsername(String username);

    // Validate user password
    @Select("select password from t_user where id=#{id};")
    public String getUserPassword(int id);

    // Change user password
    @Update("update t_user set password = #{password} where id=#{id};")
    public int updateUserPassword(String password, int id);
}
