package com.two.factor.authentication.data.dao.timerecord.impl;

import com.two.factor.authentication.TimeRecordApplication;
import com.two.factor.authentication.appl.model.time.TimeRecord;
import com.two.factor.authentication.data.connectionhelper.ConnectionHelper;
import com.two.factor.authentication.data.dao.timerecord.TimeRecordDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TimeRecordDaoImpl implements TimeRecordDao {
    public boolean addTimeRecord(TimeRecordApplication timeRecord) {
        String INSERT_TIME_RECORD_STATEMENT = "INSERT INTO time_records (employee_no, time_in, time_out, total_hours, created_at) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TIME_RECORD_STATEMENT)) {

            preparedStatement.setInt(1, timeRecord.getEmployeeNo());
            preparedStatement.setLong(2, timeRecord.getTimeIn());
            preparedStatement.setLong(3, timeRecord.getTimeOut());
            preparedStatement.setDouble(4, timeRecord.getTotalHours());
            preparedStatement.setTimestamp(5, new java.sql.Timestamp(timeRecord.getCreatedAt().getTime()));

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException ex) {
            ex.printStackTrace(); // For debugging
            return false;
        }
    }

    @Override
    public boolean updateTimeRecord(TimeRecord timeRecord) {
        return false;
    }

    @Override
    public TimeRecord addTimeRecord() {
        return null;
    }
}
