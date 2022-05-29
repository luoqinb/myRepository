package com.wss.wssadminclient.dao.impl;

import com.wss.wssadminclient.dao.WorkerDao;
import com.wss.wssadminclient.exception.NoSuchWorkerException;
import com.wss.wssadminclient.mapper.WorkerMapper;
import com.wss.wssadminclient.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service("workerDao")
public class WorkerDaoImpl implements WorkerDao {

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public List<Worker> getAllWorkers() {
        return workerMapper.getAllWorkers();
    }

    @Override
    public int addWorker(Worker worker, int uid) {
        return workerMapper.addWorker(worker.getName(), worker.getWid(), worker.getStatus(), uid, worker.getGender());
    }

    @Override
    public int resignWorker(int id)  {
        return workerMapper.resignWorker(id);
    }

    @Override
    public Worker getWorker(String wid) {
        return workerMapper.getWorker(wid);
    }

    @Override
    public Worker getWorkerByUid(int uid) {
        return workerMapper.getWorkerByUid(uid);
    }

    @Override
    public Worker getWorkerById(int id) {
        return workerMapper.getWorkerById(id);
    }

    @Override
    public int updateWorkerById(String name, String gender, int id) {
        return workerMapper.updateWorkerById(name, gender, id);
    }

}
