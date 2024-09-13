import com.two.factor.authentication.TimeRecordApplication;
//import com.two.factor.authentication.appl.facade.authentication.AuthenticationFacade;
import com.two.factor.authentication.appl.facade.timerecord.TimeRecordFacade;
//import com.two.factor.authentication.appl.model.authentication.Authentication;
import com.two.factor.authentication.appl.model.time.TimeRecord;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static TimeRecordFacade timeRecordFacade; // Facade object to manage time records

    public static void main(String[] args) {
        // Initialize your facades as needed (this can be done via Dependency Injection, hardcoded here for simplicity)
        TimeRecordApplication app = new TimeRecordApplication(timeRecordFacade);

        System.out.println("Enter Employee Number: ");
        int employeeNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter OTP Code: ");
        String otpCode = scanner.nextLine();

        // Assuming the OTP is verified via an authentication facade or similar service
        boolean isAuthenticated = authenticationFacade.verifyOTP(employeeNumber, otpCode);
        if (isAuthenticated) {
            // You can now call methods on the app object to time in or time out
            app.timeIn(employeeNumber);
            // Similarly, for time out, you would call app.timeOut(employeeNumber);
        } else {
            System.out.println("Invalid OTP Code.");
        }
    }
}
