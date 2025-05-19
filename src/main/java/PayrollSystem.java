/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elias
 */
import java.util.List;

public class PayrollSystem {
    public double generatePayslip(Employee employee) {
        List<AttendanceLog> logs = AttendanceLog.getAttendanceForEmployee(employee.getEmployeeNumber());
        double totalHours = 0;
        for (AttendanceLog log : logs) {
            totalHours += log.calculateHoursWorked();
        }

        double grossPay = PayrollCalculator.calculateTotalEarnings(totalHours, employee.getRatePerHour());

        double totalDeductions = Deductions.calculateSSS(grossPay) +
                                 Deductions.calculatePhilHealth(grossPay) +
                                 Deductions.calculatePagIbig(grossPay) +
                                 Deductions.calculateWithholdingTax(grossPay);

        return grossPay - totalDeductions;
    }
}

