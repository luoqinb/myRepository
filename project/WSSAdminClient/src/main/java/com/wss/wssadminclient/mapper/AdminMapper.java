package com.wss.wssadminclient.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {
    // Admin Client only
    // Get admin password
    @Select("select value from t_settings where attributes='adminPassword';")
    public String getAdminPassword();

    // Change admin password
    @Update("update t_settings set value=#{password} where attributes='adminPassword'")
    public int updateAdminPassword(String password);

}
