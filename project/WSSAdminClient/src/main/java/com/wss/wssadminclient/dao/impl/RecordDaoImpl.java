package com.wss.wssadminclient.dao.impl;

import com.wss.wssadminclient.dao.RecordDao;
import com.wss.wssadminclient.mapper.RecordMapper;
import com.wss.wssadminclient.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("recordDao")
public class RecordDaoImpl implements RecordDao {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public List<Record> getAllRecords() {
        return recordMapper.getAllRecords();
    }

    @Override
    public List<Record> getUserRecords(int uid) {
        return recordMapper.getUserRecords(uid);
    }

    @Override
    public List<Record> getSubmittedRecords() {
        return recordMapper.getSubmittedRecords();
    }

    @Override
    public List<Record> getAcceptedRecords() {
        return recordMapper.getAcceptedRecords();
    }

    @Override
    public List<Record> getRefusedRecords() {
        return recordMapper.getRefusedRecords();
    }


    @Override
    public int acceptRecord(int id) {
        return recordMapper.acceptRecord(id);
    }

    @Override
    public int refuseRecord(int id) {
        return recordMapper.refuseRecord(id);
    }

    @Override
    public List<Record> getAllSalary() {
        return recordMapper.getAllSalary();
    }

    @Override
    public List<Record> getAllSalaryByWid(String wid) {
        return recordMapper.getAllSalaryByWid(wid);
    }

    @Override
    public List<Record> getSalaryByYearMonth(String yearMonth) {
        return recordMapper.getSalaryByYearMonth(yearMonth + "%");
    }

    @Override
    public List<Record> getSalaryByBoth(String wid, String yearMonth) {
        return recordMapper.getSalaryByBoth(wid, yearMonth + "%");
    }


}
