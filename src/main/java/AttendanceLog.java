/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elias
 */
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceLog {
    private String employeeNumber;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;

    private static List<AttendanceLog> attendanceList = new ArrayList<>();

    public AttendanceLog(String employeeNumber, LocalDateTime timeIn, LocalDateTime timeOut) {
        this.employeeNumber = employeeNumber;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public static void addAttendance(AttendanceLog log) {
        attendanceList.add(log);
    }

    public static List<AttendanceLog> getAttendanceForEmployee(String employeeNumber) {
        List<AttendanceLog> result = new ArrayList<>();
        for (AttendanceLog log : attendanceList) {
            if (log.employeeNumber.equals(employeeNumber)) {
                result.add(log);
            }
        }
        return result;
    }

    public double calculateHoursWorked() {
        return (double) java.time.Duration.between(timeIn, timeOut).toMinutes() / 60;
    }
}

