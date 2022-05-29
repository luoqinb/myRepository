package com.wss.wssadminclient.dao;

import com.wss.wssadminclient.model.Record;

import java.util.List;

public interface RecordDao {

    // Get all records
    public List<Record> getAllRecords();

    // Get user records
    public List<Record> getUserRecords(int uid);

    // Get submitted records
    public List<Record> getSubmittedRecords();

    // Get accepted records
    public List<Record> getAcceptedRecords();

    // Get refused records
    public List<Record> getRefusedRecords();

    // Admin accepts a record
    public int acceptRecord(int id);

    // Admin refuses a record (or marked as deleted)
    public int refuseRecord(int id);

    public List<Record> getAllSalary();

    public List<Record> getAllSalaryByWid(String wid);

    public List<Record> getSalaryByYearMonth(String yearMonth);
    public List<Record> getSalaryByBoth(String wid, String yearMonth);
}
