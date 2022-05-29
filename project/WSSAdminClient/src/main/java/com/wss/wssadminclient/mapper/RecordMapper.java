package com.wss.wssadminclient.mapper;

import com.wss.wssadminclient.model.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RecordMapper {

    // Get all records
    @Select("select * from t_record;")
    public List<Record> getAllRecords();

    // Get record by user ID
    @Select("select * from t_record where uid=#{uid};")
    public List<Record> getUserRecords(int uid);

    // Get submitted records
    @Select("select * from t_record where status=1;")
    public List<Record> getSubmittedRecords();

    // Get accepted records
    @Select("select * from t_record where status=2;")
    public List<Record> getAcceptedRecords();

    // Get refused records
    @Select("select * from t_record where status=3;")
    public List<Record> getRefusedRecords();

    // User submits a new record
//    @Insert("insert into t_record (uid, pid, price, status) values (#{uid}, #{pid}, #{price}, 1);")
//    public int submitNewRecord(int uid, int pid, double price);

    // Admin accepts a record
    @Update("update t_record set status = 2 where id=#{id};")
    public int acceptRecord(int id);

    // Admin refuses a record (or marked as deleted)
    @Update("update t_record set status = 3 where id=#{id};")
    public int refuseRecord(int id);

    // Query all salary
    @Select("select t_worker.wid as wid, t_worker.name wname, t_product.pid productId, t_product.price productPrice, t_record.amount amount, t_record.dateTime dateTime, t_record.price price from t_record, t_product, t_worker where t_record.status=2 and t_record.pid=t_product.id and t_record.uid=t_worker.uid")
    public List<Record> getAllSalary();

    //todo
//    @Select("select * from wss.t_record;")
//    public List<Record> testGetSalary();

    // Query salary by wid
    @Select("select t_worker.wid as wid, t_worker.name wname, t_product.pid productId, t_product.price productPrice, t_record.amount amount, t_record.dateTime dateTime, t_record.price price from t_record, t_product, t_worker where t_record.status=2 and t_worker.wid=#{wid} and t_record.pid=t_product.id and t_record.uid=t_worker.uid;")
    public List<Record> getAllSalaryByWid(String wid);

    // Query salary by dateTime
    @Select("select t_worker.wid as wid, t_worker.name wname, t_product.pid productId, t_product.price productPrice, t_record.amount amount, t_record.dateTime dateTime, t_record.price price from t_record, t_product, t_worker where t_record.status=2 and dateTime like #{yearMonth} and t_record.pid=t_product.id and t_record.uid=t_worker.uid;")
    public List<Record> getSalaryByYearMonth(String yearMonth);

    // Query salary by both
    @Select("select t_worker.wid as wid, t_worker.name wname, t_product.pid productId, t_product.price productPrice, t_record.amount amount, t_record.dateTime dateTime, t_record.price price from t_record, t_product, t_worker where t_record.status=2 and t_worker.wid=#{wid} and dateTime like #{yearMonth} and t_record.pid=t_product.id and t_record.uid=t_worker.uid;")
    public List<Record> getSalaryByBoth(String wid, String yearMonth);
}
