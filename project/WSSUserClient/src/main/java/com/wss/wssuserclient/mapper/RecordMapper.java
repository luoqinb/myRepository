package com.wss.wssuserclient.mapper;

import com.wss.wssuserclient.model.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecordMapper {

    // Get all records
    @Select("select * from t_record;")
    public List<Record> getAllRecords() throws Exception;

    // Get user's all records
    @Select("select * from t_record where uid=#{uid};")
    public List<Record> getRecordsByUid(int uid);

    // User submits a new record
    @Insert("insert into t_record (uid, pid, price, status, dateTime, amount) values (#{uid}, #{pid}, #{price}, 1, #{dateTime}, #{amount});")
    public int submitNewRecord(int uid, int pid, double price, String dateTime, int amount);

    // Search records by yearMonth
    @Select("select * from t_record where status=2 and dateTime like #{yearMonth};")
    public List<Record> searchRecords(String yearMonth);

    // Get salary of a yearMonth
    @Select("select SUM(price) from t_record where status=2 and dateTime like #{yearMonth};")
    public Double salaryOfYearMonth(String yearMonth);

}
