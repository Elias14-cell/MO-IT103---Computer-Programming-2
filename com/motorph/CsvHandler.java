package com.motorph;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CsvHandler {
    public Map<String, Employee> loadEmployees(File file) throws IOException {
        Map<String, Employee> employees = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine(); // Skip header

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",", -1);
            if (parts.length < 17) continue;

            String empNo = parts[0];
            String lastName = parts[1];
            String firstName = parts[2];
            String birthday = parts[3];
            double hourlyRate = Double.parseDouble(parts[16].replace(",", "").trim());

            employees.put(empNo, new Employee(empNo, firstName, lastName, birthday, hourlyRate));
        }
        return employees;
    }

    public List<AttendanceLog> loadAttendance(File file) throws IOException {
        List<AttendanceLog> logs = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine(); // Skip header

        String line;
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("H:mm");

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",", -1);
            if (parts.length < 6) continue;

            String empNo = parts[0];
            LocalDate date = LocalDate.parse(parts[3].trim(), dateFmt);
            LocalTime login = LocalTime.parse(parts[4].trim(), timeFmt);
            LocalTime logout = LocalTime.parse(parts[5].trim(), timeFmt);

            logs.add(new AttendanceLog(empNo, date, login, logout));
        }

        return logs;
    }
}