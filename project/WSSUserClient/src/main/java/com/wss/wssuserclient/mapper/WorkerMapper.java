package com.wss.wssuserclient.mapper;

import com.wss.wssuserclient.exception.NoSuchWorkerException;
import com.wss.wssuserclient.model.Worker;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface WorkerMapper {

    // Add new worker
    @Insert("insert into t_worker (name, wid, status) values (#{name}, #{wid}, 1);")
    public int addWorker(String name, String wid, int status) throws NoSuchWorkerException;

    // Resign a worker
    @Update("update t_worker set status = 2 where id=#{id};")
    public int resignWorker(int id) throws NoSuchWorkerException;

    // Get worker by ID
    @Select("select * from t_worker where id=#{id};")
    public Worker getWorkerById(int id);


}
