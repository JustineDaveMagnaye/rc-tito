package com.two.factor.authentication.data.dao.timerecord;

import com.two.factor.authentication.appl.model.time.TimeRecord;

import java.sql.Timestamp;

public interface TimeRecordDao {
    TimeRecord addTimeRecord(String employeeNo, Timestamp timeIn, Timestamp timeOut, double totalHours);
    TimeRecord getTimeRecordByEmployeeNo(String employeeNo);
    TimeRecord updateTimeRecord(TimeRecord timeRecord); // New method to update existing records
    boolean checkEmployeeExists(String employeeNo, Timestamp timeIn);
}
