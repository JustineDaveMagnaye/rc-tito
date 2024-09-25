package com.two.factor.authentication.data.utils;

public class QueryConstants {
    public static final String INSERT_TIME_RECORD_STATEMENT = "INSERT INTO timerecord (employee_no, time_in, time_out, total_hours, created_at) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_TIME_RECORD_STATEMENT = "SELECT timeIn, timeOut, totalHours FROM time_record WHERE employeeNo = ? AND TRUNC(timeIn) = TRUNC(SYSDATE)";
    public static final String UPDATE_TIME_RECORD_STATEMENT = "UPDATE timerecord SET timeIn = ?, timeOut = ?, totalHours = ? WHERE employeeNo = ? AND TRUNC(timeIn) = TRUNC(SYSDATE)";
    public static final String CHECK_EMPLOYEE_EXISTS_STATEMENT = "SELECT COUNT(*) FROM time_record WHERE employee_no = ? AND TRUNC(time_in) = TRUNC(SYSDATE)";
}
