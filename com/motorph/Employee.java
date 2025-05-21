package com.motorph;

public class Employee {
    private String empNumber;
    private String firstName;
    private String lastName;
    private String birthday;
    private double hourlyRate;

    public Employee(String empNumber, String firstName, String lastName, String birthday, double hourlyRate) {
        this.empNumber = empNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.hourlyRate = hourlyRate;
    }

    public String getEmpNumber() { return empNumber; }
    public String getFullName() { return firstName + " " + lastName; }
    public double getHourlyRate() { return hourlyRate; }
}