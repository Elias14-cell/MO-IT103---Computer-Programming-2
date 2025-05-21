package com.motorph;

import java.util.List;

public class PayrollCalculator {

    public double calculateGross(List<AttendanceLog> logs, double hourlyRate) {
        double totalHours = 0;
        for (AttendanceLog log : logs) {
            long minutesWorked = java.time.Duration.between(log.getLoginTime(), log.getLogoutTime()).toMinutes();
            totalHours += minutesWorked / 60.0;
        }
        return totalHours * hourlyRate;
    }

    public double calculateNet(double gross) {
        double sss = calculateSSS(gross);
        double philhealth = Math.min(gross * 0.03, 1800);
        double pagibig = gross * 0.02;
        double withholding = calculateWithholdingTax(gross);
        return gross - (sss + philhealth + pagibig + withholding);
    }

    private double calculateSSS(double gross) {
        if (gross <= 3250) return 135;
        if (gross <= 24750) return 1125;
        return 1350;
    }

    private double calculateWithholdingTax(double gross) {
        if (gross <= 20833) return 0;
        if (gross <= 33332) return (gross - 20833) * 0.20;
        return (gross - 33332) * 0.25 + 2500;
    }
}