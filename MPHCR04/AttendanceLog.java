/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elias
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

// AttendanceLog class
class AttendanceLog {
    private String empNumber;
    private LocalDate date;
    private LocalDateTime loginDateTime;
    private LocalDateTime logoutDateTime;
    
    private static List<AttendanceLog> allLogs = new ArrayList<>();

    public AttendanceLog(String empNumber, LocalDateTime loginDateTime, LocalDateTime logoutDateTime) {
        this.empNumber = empNumber;
        this.loginDateTime = loginDateTime;
        this.logoutDateTime = logoutDateTime;
        this.date = loginDateTime.toLocalDate();
    }

    public String getEmpNumber() { return empNumber; }
    public LocalDate getDate() { return date; }
    public LocalDateTime getLoginDateTime() { return loginDateTime; }
    public LocalDateTime getLogoutDateTime() { return logoutDateTime; }

    public double calculateHoursWorked() {
        if (loginDateTime != null && logoutDateTime != null) {
            long minutes = java.time.Duration.between(loginDateTime, logoutDateTime).toMinutes();
            return minutes / 60.0;
        }
        return 0.0;
    }

    public static void addAttendance(AttendanceLog log) {
        allLogs.add(log);
    }

    public static List<AttendanceLog> getAttendanceForEmployee(String empNumber) {
        return allLogs.stream()
                .filter(log -> log.getEmpNumber().equals(empNumber))
                .collect(java.util.stream.Collectors.toList());
    }

    public static void clearAllLogs() {
        allLogs.clear();
    }
}
