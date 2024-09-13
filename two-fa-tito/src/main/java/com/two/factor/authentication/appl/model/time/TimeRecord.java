package com.two.factor.authentication.appl.model.time;

import java.util.Date;

public class TimeRecord {
    private int employeeNo;
    private long timeIn;
    private long timeOut;
    private double totalHours;
    private Date createdAt;

    public TimeRecord(int employeeNo) {
        this.employeeNo = employeeNo;
        this.timeIn = -1;
        this.timeOut = -1;
        this.totalHours = 0;
        this.createdAt = new Date(); // Set createdAt to the current date
    }

    public int getEmployeeNo() {
        return employeeNo;
    }

    public long getTimeIn() {
        return timeIn;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void timeIn() {
        this.timeIn = System.currentTimeMillis();
    }

    public void timeOut() {
        this.timeOut = System.currentTimeMillis();
        updateTotalHours(); // Update totalHours when timeOut is set
    }

    private void updateTotalHours() {
        if (timeIn != -1 && timeOut != -1) {
            long durationMillis = timeOut - timeIn;
            this.totalHours = durationMillis / 3600000.0; // Convert milliseconds to hours
        }
    }

    public boolean hasTimedIn() {
        return timeIn != -1;
    }
}
