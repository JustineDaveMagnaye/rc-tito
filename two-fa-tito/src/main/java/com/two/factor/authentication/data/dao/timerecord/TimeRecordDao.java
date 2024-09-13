package com.two.factor.authentication.data.dao.timerecord;

import com.two.factor.authentication.TimeRecordApplication;
import com.two.factor.authentication.appl.model.time.TimeRecord;

public interface TimeRecordDao {
    boolean addTimeRecord(TimeRecordApplication timeRecord);

    boolean updateTimeRecord(TimeRecord timeRecord);

    TimeRecord addTimeRecord();
}
