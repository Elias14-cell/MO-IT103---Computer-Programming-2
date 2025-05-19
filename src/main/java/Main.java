/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elias
 */
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Create an employee
        Employee emp = new Employee("EMP001", "Cruz", "Juan", "Developer", 200.0);

        // Log attendance (sample: 2 days)
        AttendanceLog.addAttendance(new AttendanceLog("EMP001",
                LocalDateTime.of(2025, 5, 14, 9, 0),
                LocalDateTime.of(2025, 5, 14, 17, 0)));

        AttendanceLog.addAttendance(new AttendanceLog("EMP001",
                LocalDateTime.of(2025, 5, 15, 9, 30),
                LocalDateTime.of(2025, 5, 15, 18, 0)));

        // Generate payslip
        PayrollSystem payrollSystem = new PayrollSystem();
        double netPay = payrollSystem.generatePayslip(emp);

        // Output result
        System.out.println("Payslip for: " + emp.getFullName());
        System.out.println("Employee Number: " + emp.getEmployeeNumber());
        System.out.println("Net Pay: PHP " + String.format("%.2f", netPay));
    }
}

