/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elias
 */
public class Employee {
    private String employeeNumber;
    private String lastName;
    private String firstName;
    private String position;
    private double ratePerHour;

    public Employee(String employeeNumber, String lastName, String firstName, String position, double ratePerHour) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.ratePerHour = ratePerHour;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public double getRatePerHour() {
        return ratePerHour;
    }
}
