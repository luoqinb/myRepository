package com.wss.wssadminclient.dao;

import com.wss.wssadminclient.exception.NoSuchWorkerException;
import com.wss.wssadminclient.model.Worker;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface WorkerDao {
    public List<Worker> getAllWorkers();

    public int addWorker(Worker worker, int uid);

    public int resignWorker(int id);

    public Worker getWorker(String wid);

    public Worker getWorkerByUid(int uid);

    public Worker getWorkerById(int id);

    public int updateWorkerById(String name, String gender, int id);
}
