package com.two.factor.authentication;

import com.two.factor.authentication.appl.facade.timerecord.TimeRecordFacade;
import com.two.factor.authentication.appl.model.time.TimeRecord;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TimeRecordApplication {

    private TimeRecordFacade timeRecordFacade;
    private Scanner scanner = new Scanner(System.in);

    public TimeRecordApplication(TimeRecordFacade timeRecordFacade) {
        this.timeRecordFacade = timeRecordFacade;
    }

    public void run() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Time In");
            System.out.println("2. Time Out");
            System.out.println("3. Exit");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 3) {
                System.out.println("Exiting the system.");
                break;
            }

            System.out.println("Enter Employee Number: ");
            String employeeNumber = scanner.nextLine();

            processTimeInOut(choice, employeeNumber, dateFormat);
        }
    }

    private void processTimeInOut(int choice, String employeeNumber, SimpleDateFormat dateFormat) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        switch (choice) {
            case 1:
                // Time In
                System.out.println("You have chosen Time In.");
                timeIn(employeeNumber, currentTime);
                break;
            case 2:
                // Time Out
                System.out.println("You have chosen Time Out.");
                timeOut(employeeNumber, currentTime);
                break;
            default:
                System.out.println("Invalid choice. Please select 1 for Time In or 2 for Time Out.");
        }
    }

    private void timeIn(String employeeNo, Timestamp timeIn) {
        TimeRecord timeRecord = timeRecordFacade.addTimeRecord(employeeNo, timeIn, null, 0.0);
        if (timeRecord != null) {
            System.out.println("Employee " + employeeNo + " has timed in at " +
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeRecord.getTimeIn()));
        } else {
            System.out.println("Failed to add time-in for employee " + employeeNo);
        }
    }

    private void timeOut(String employeeNo, Timestamp timeOut) {
        TimeRecord timeRecord = timeRecordFacade.getTimeRecordByEmployeeNo(employeeNo);
        if (timeRecord != null && timeRecord.hasTimedIn()) {
            if (isSameDay(timeRecord.getTimeIn(), timeOut)) {
                timeRecord.setTimeOut(timeOut);
                timeRecordFacade.updateTimeRecord(timeRecord); // Update the time record
                System.out.println("Employee " + employeeNo + " has timed out at " +
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeRecord.getTimeOut()));
            } else {
                System.out.println("Failed to time out for employee " + employeeNo +
                        ". Time out must be on the same day as time in.");
            }
        } else {
            System.out.println("Failed to time out for employee " + employeeNo +
                    " or no time in record found.");
        }
    }
    private boolean isSameDay(Timestamp timeIn, Timestamp timeOut) {
        return false;
    }
}
