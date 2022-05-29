package com.wss.wssuserclient.dao;

import com.wss.wssuserclient.model.Record;

import java.util.List;

public interface RecordDao {

    // Get all records
    public List<Record> getAllRecords() throws Exception;

    // User submits a new record
    public int submitNewRecord(Record record);

    // Get user's all records
    public List<Record> getRecordsByUid(int uid);

    // Search records by year and month
    public List<Record> getRecordsByYearMonth(String yearMonth);

    // Get salary of year-month
    public Double salaryOfYearMonth(String yearMonth);
}
