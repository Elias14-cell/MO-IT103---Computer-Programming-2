/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elias
 */
import java.io.*;

// Employee class
class Employee {
    private String empNumber;
    private String firstName;
    private String lastName;
    private String birthday;
    private String sssNumber;
    private String philHealthNumber;
    private String tin;
    private String pagibigNumber;
    private double hourlyRate;

    public Employee(String empNumber, String firstName, String lastName, String birthday, double hourlyRate) {
        this.empNumber = empNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.hourlyRate = hourlyRate;
        this.sssNumber = "";
        this.philHealthNumber = "";
        this.tin = "";
        this.pagibigNumber = "";
    }

    public Employee(String empNumber, String firstName, String lastName, String birthday, 
                   String sssNumber, String philHealthNumber, String tin, String pagibigNumber, double hourlyRate) {
        this.empNumber = empNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sssNumber = sssNumber;
        this.philHealthNumber = philHealthNumber;
        this.tin = tin;
        this.pagibigNumber = pagibigNumber;
        this.hourlyRate = hourlyRate;
    }

    // Getters and Setters
    public String getEmployeeNumber() { return empNumber; }
    public String getEmpNumber() { return empNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFullName() { return firstName + " " + lastName; }
    public String getBirthday() { return birthday; }
    public String getSssNumber() { return sssNumber; }
    public String getPhilHealthNumber() { return philHealthNumber; }
    public String getTin() { return tin; }
    public String getPagibigNumber() { return pagibigNumber; }
    public double getHourlyRate() { return hourlyRate; }
    public double getRatePerHour() { return hourlyRate; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setBirthday(String birthday) { this.birthday = birthday; }
    public void setSssNumber(String sssNumber) { this.sssNumber = sssNumber; }
    public void setPhilHealthNumber(String philHealthNumber) { this.philHealthNumber = philHealthNumber; }
    public void setTin(String tin) { this.tin = tin; }
    public void setPagibigNumber(String pagibigNumber) { this.pagibigNumber = pagibigNumber; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }


   }
