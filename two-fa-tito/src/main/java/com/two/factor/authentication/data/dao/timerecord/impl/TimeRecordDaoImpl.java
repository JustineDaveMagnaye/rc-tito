package com.two.factor.authentication.data.dao.timerecord.impl;

import com.two.factor.authentication.appl.model.time.TimeRecord;
import com.two.factor.authentication.data.connectionhelper.ConnectionHelper;
import com.two.factor.authentication.data.dao.timerecord.TimeRecordDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import static com.two.factor.authentication.data.utils.QueryConstants.*;

public class TimeRecordDaoImpl implements TimeRecordDao {

    @Override
    public TimeRecord addTimeRecord(String employeeNo, Timestamp timeIn, Timestamp timeOut, double totalHours) {
        TimeRecord timeRecord = null;

        try (Connection connection = ConnectionHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TIME_RECORD_STATEMENT);
            preparedStatement.setString(1, employeeNo);
            preparedStatement.setTimestamp(2, timeIn);
            preparedStatement.setTimestamp(3, timeOut);
            preparedStatement.setDouble(4, totalHours);
            preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis())); // Created at

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                timeRecord = new TimeRecord(employeeNo);
                timeRecord.setTimeIn(timeIn);
                timeRecord.setTimeOut(timeOut);
                timeRecord.setTotalHours(totalHours);
                timeRecord.setCreatedAt(new Timestamp(System.currentTimeMillis())); // Set created at
            }
        } catch (Exception e) {
            System.out.println("Error adding time record: " + e.getMessage());
        }

        return timeRecord;
    }

    @Override
    public TimeRecord getTimeRecordByEmployeeNo(String employeeNo) {
        TimeRecord timeRecord = null;

        try (Connection connection = ConnectionHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TIME_RECORD_STATEMENT);
            preparedStatement.setString(1, employeeNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Timestamp timeIn = resultSet.getTimestamp("timeIn");
                Timestamp timeOut = resultSet.getTimestamp("timeOut");
                double totalHours = resultSet.getDouble("totalHours");

                timeRecord = new TimeRecord(employeeNo);
                timeRecord.setTimeIn(timeIn);
                timeRecord.setTimeOut(timeOut);
                timeRecord.setTotalHours(totalHours);
                timeRecord.setCreatedAt(new Timestamp(System.currentTimeMillis())); // or fetch from DB if you have a created_at column
            }
        } catch (Exception e) {
            System.out.println("Error fetching time record: " + e.getMessage());
        }

        return timeRecord;
    }

    @Override
    public TimeRecord updateTimeRecord(TimeRecord timeRecord) {
        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TIME_RECORD_STATEMENT)) {

            preparedStatement.setTimestamp(1, timeRecord.getTimeIn());
            preparedStatement.setTimestamp(2, timeRecord.getTimeOut());
            preparedStatement.setDouble(3, timeRecord.getTotalHours());
            preparedStatement.setString(4, timeRecord.getEmployeeNo());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Time record updated successfully for employee " + timeRecord.getEmployeeNo());
                return timeRecord;
            }
        } catch (Exception e) {
            System.out.println("Error updating time record: " + e.getMessage());
        }

        return null;
    }
    @Override
    public boolean checkEmployeeExists(String employeeNo, Timestamp timeIn) {
        // Use the query constant for checking employee existence
        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EMPLOYEE_EXISTS_STATEMENT)) {
            preparedStatement.setString(1, employeeNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Check if count is greater than 0
            }
        } catch (Exception e) {
            System.out.println("Error checking employee existence: " + e.getMessage());
        }
        return false; // Return false if an exception occurs or no records found
    }

}
