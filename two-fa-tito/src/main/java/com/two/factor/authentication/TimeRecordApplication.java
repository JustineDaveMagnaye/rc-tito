package com.two.factor.authentication;

import com.two.factor.authentication.appl.facade.timerecord.TimeRecordFacade;
import com.two.factor.authentication.appl.model.time.TimeRecord;

public class TimeRecordApplication {
    private TimeRecordFacade timeRecordFacade; // Not final as it can be set later

    public TimeRecordApplication(TimeRecordFacade timeRecordFacade) {
        this.timeRecordFacade = timeRecordFacade;
    }

    // Method to add a new time record for an employee
    public void addTimeRecord(int employeeNo, String name) {
        if (timeRecordFacade.addTimeRecord(employeeNo).hasTimedIn()) {
            System.out.println("Time record for " + name + " has been added.");
        } else {
            System.out.println("Failed to add time record for " + name);
        }
    }

    // Method to record time-in for a given employeeNo
    public void timeIn(int employeeNo) {
        TimeRecord timeRecord = timeRecordFacade.addTimeRecord(employeeNo); // Fetch the time record for the employee
        if (timeRecord != null) {
            if (!timeRecord.hasTimedIn()) {
                timeRecord.timeIn(); // Set time-in
                if (timeRecordFacade.updateTimeRecord(timeRecord)) {
                    System.out.println("Employee " + employeeNo + " has timed in at " + timeRecord.getTimeIn());
                } else {
                    System.out.println("Failed to update time-in for employee " + employeeNo);
                }
            } else {
                System.out.println("Employee " + employeeNo + " has already timed in.");
            }
        } else {
            System.out.println("Time record not found for employee " + employeeNo);
        }
    }

    // Method to record time-out for a given employeeNo
    public void timeOut(int employeeNo) {
        TimeRecord timeRecord = timeRecordFacade.addTimeRecord(employeeNo); // Fetch the time record for the employee
        if (timeRecord != null) {
            if (timeRecord.hasTimedIn()) {
                timeRecord.timeOut(); // Set time-out
                if (timeRecordFacade.updateTimeRecord(timeRecord)) {
                    System.out.println("Employee " + employeeNo + " has timed out at " + timeRecord.getTimeOut());
                } else {
                    System.out.println("Failed to update time-out for employee " + employeeNo);
                }
            } else {
                System.out.println("Employee " + employeeNo + " hasn't timed in yet.");
            }
        } else {
            System.out.println("Time record not found for employee " + employeeNo);
        }
    }

    public TimeRecordFacade getTimeRecordFacade() {
        return timeRecordFacade;
    }

    public void setTimeRecordFacade(TimeRecordFacade timeRecordFacade) {
        this.timeRecordFacade = timeRecordFacade;
    }
}
