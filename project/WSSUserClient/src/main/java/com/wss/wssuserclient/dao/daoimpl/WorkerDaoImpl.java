package com.wss.wssuserclient.dao.daoimpl;

import com.wss.wssuserclient.dao.WorkerDao;
import com.wss.wssuserclient.exception.NoSuchWorkerException;
import com.wss.wssuserclient.mapper.WorkerMapper;
import com.wss.wssuserclient.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("workerDao")
public class WorkerDaoImpl implements WorkerDao {

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public int addWorker(Worker worker) throws NoSuchWorkerException {
        return workerMapper.addWorker(worker.getName(), worker.getWid(), worker.getStatus());
    }

    @Override
    public int resignWorker(Worker worker) throws NoSuchWorkerException {
        return workerMapper.resignWorker(worker.getId());
    }

    @Override
    public Worker getWorkerById(int id) {
        return workerMapper.getWorkerById(id);
    }

}
