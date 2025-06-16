/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elias
 */
import java.util.List;

// PayrollSystem class
class PayrollSystem {
    public PayslipDetails generateDetailedPayslip(Employee employee) {
        List<AttendanceLog> logs = AttendanceLog.getAttendanceForEmployee(employee.getEmployeeNumber());
        
        double totalHours = 0;
        for (AttendanceLog log : logs) {
            totalHours += log.calculateHoursWorked();
        }

        double grossPay = PayrollCalculator.calculateTotalEarnings(totalHours, employee.getRatePerHour());
        double sss = Deductions.calculateSSS(grossPay);
        double philHealth = Deductions.calculatePhilHealth(grossPay);
        double pagibig = Deductions.calculatePagIbig(grossPay);
        double withholdingTax = Deductions.calculateWithholdingTax(grossPay);
        double totalDeductions = sss + philHealth + pagibig + withholdingTax;
        double netPay = grossPay - totalDeductions;

        return new PayslipDetails(employee, totalHours, grossPay, sss, philHealth, 
                                pagibig, withholdingTax, totalDeductions, netPay);
    }
}
