package com.wss.wssadminclient.mapper;

import com.wss.wssadminclient.exception.NoSuchWorkerException;
import com.wss.wssadminclient.model.Worker;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Mapper
public interface WorkerMapper {
    // Get all workers
    @Select("select * from t_worker;")
    public List<Worker> getAllWorkers();

    // Add new worker
    @Insert("insert into t_worker (name, wid, status, uid, gender) values (#{name}, #{wid}, 1, #{uid}, #{gender});")
    public int addWorker(String name, String wid, int status, int uid, String gender);

    // Resign a worker
    @Update("update t_worker set status = 2 where id=#{id};")
    public int resignWorker(int id);

    // Get worker by worker id
    @Select("select * from t_worker where wid=#{wid};")
    public Worker getWorker(String wid);

    // Get worker by uid
    @Select("select * from t_worker where uid=#{uid};")
    public Worker getWorkerByUid(int uid);

    // Get worker by id
    @Select("select * from t_worker where id=#{id};")
    public Worker getWorkerById(int id);

    // Update worker info
    @Update("update t_worker set gender = #{gender}, name = #{name} where id=#{id};")
    public int updateWorkerById(String name, String gender, int id);
}
