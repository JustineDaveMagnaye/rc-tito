package com.two.factor.authentication.data.utils;

public class QueryConstants {

    public static final String VALIDATE_OTP_STATEMENT = "INSERT INTO authenticator (employee_no, authenticator_code, created_at) VALUES (?, ?, ?)";
}
