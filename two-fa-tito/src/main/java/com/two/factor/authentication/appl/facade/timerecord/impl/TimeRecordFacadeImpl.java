package com.two.factor.authentication.appl.facade.timerecord.impl;

import com.two.factor.authentication.appl.facade.timerecord.TimeRecordFacade;
import com.two.factor.authentication.appl.model.time.TimeRecord;
import com.two.factor.authentication.data.dao.timerecord.TimeRecordDao;

public class TimeRecordFacadeImpl implements TimeRecordFacade {

    private TimeRecordDao timeRecordDao;

    public TimeRecordFacadeImpl(TimeRecordDao timeRecordDao) {this.timeRecordDao = timeRecordDao;}

    @Override
    public TimeRecord addTimeRecord(int employeeNo) throws RuntimeException {
        try {
            return timeRecordDao.addTimeRecord();
        } catch (Exception e) {
            throw new RuntimeException("Failed to add time record", e);
        }
    }

    @Override
    public boolean updateTimeRecord(TimeRecord timeRecord) {
        try {
            return timeRecordDao.updateTimeRecord(timeRecord);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add time record", e);
        }
    }
}
