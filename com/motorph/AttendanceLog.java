package com.motorph;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceLog {
    private String empNumber;
    private LocalDate date;
    private LocalTime loginTime;
    private LocalTime logoutTime;

    public AttendanceLog(String empNumber, LocalDate date, LocalTime login, LocalTime logout) {
        this.empNumber = empNumber;
        this.date = date;
        this.loginTime = login;
        this.logoutTime = logout;
    }

    public String getEmpNumber() { return empNumber; }
    public LocalDate getDate() { return date; }
    public LocalTime getLoginTime() { return loginTime; }
    public LocalTime getLogoutTime() { return logoutTime; }
}