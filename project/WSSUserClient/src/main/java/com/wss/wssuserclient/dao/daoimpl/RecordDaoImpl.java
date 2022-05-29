package com.wss.wssuserclient.dao.daoimpl;

import com.wss.wssuserclient.dao.RecordDao;
import com.wss.wssuserclient.mapper.RecordMapper;
import com.wss.wssuserclient.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("recordDao")
public class RecordDaoImpl implements RecordDao {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public List<Record> getAllRecords() throws Exception {
        return recordMapper.getAllRecords();
    }

    @Override
    public int submitNewRecord(Record record) {
        return recordMapper.submitNewRecord(record.getUid(), record.getPid(), record.getPrice(), record.getDateTime(), record.getAmount());
    }

    @Override
    public List<Record> getRecordsByUid(int uid) {
        return recordMapper.getRecordsByUid(uid);
    }

    @Override
    public List<Record> getRecordsByYearMonth(String yearMonth) {
        return recordMapper.searchRecords(yearMonth + "%");
    }

    @Override
    public Double salaryOfYearMonth(String yearMonth) {
        return recordMapper.salaryOfYearMonth(yearMonth + "%");
    }

}
