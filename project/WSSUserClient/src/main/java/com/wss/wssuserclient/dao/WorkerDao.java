package com.wss.wssuserclient.dao;

import com.wss.wssuserclient.exception.NoSuchWorkerException;
import com.wss.wssuserclient.model.Worker;

public interface WorkerDao {

    public int addWorker(Worker worker) throws NoSuchWorkerException;

    public int resignWorker(Worker worker) throws NoSuchWorkerException;

    public Worker getWorkerById(int id);
}
