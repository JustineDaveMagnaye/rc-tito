package com.two.factor.authentication.appl.facade.timerecord;

import com.two.factor.authentication.appl.model.time.TimeRecord;

public interface TimeRecordFacade {

    TimeRecord addTimeRecord(int employeeNo) throws RuntimeException;

    boolean updateTimeRecord(TimeRecord timeRecord);
}
