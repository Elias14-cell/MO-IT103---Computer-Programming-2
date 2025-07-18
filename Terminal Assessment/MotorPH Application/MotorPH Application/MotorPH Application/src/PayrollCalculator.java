/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elias
 */
import java.util.List;

// PayrollCalculator class
class PayrollCalculator {
    public static double calculateTotalEarnings(double totalHours, double hourlyRate) {
        return totalHours * hourlyRate;
    }

    public static double calculateGross(List<AttendanceLog> logs, double hourlyRate) {
        double totalHours = 0;
        for (AttendanceLog log : logs) {
            totalHours += log.calculateHoursWorked();
        }
        return calculateTotalEarnings(totalHours, hourlyRate);
    }
}
