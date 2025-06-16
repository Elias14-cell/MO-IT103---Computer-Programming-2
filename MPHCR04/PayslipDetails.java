/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elias
 */
class PayslipDetails {
    private Employee employee;
    private double totalHours;
    private double grossPay;
    private double sss;
    private double philHealth;
    private double pagibig;
    private double withholdingTax;
    private double totalDeductions;
    private double netPay;

    public PayslipDetails(Employee employee, double totalHours, double grossPay, 
                        double sss, double philHealth, double pagibig, 
                        double withholdingTax, double totalDeductions, double netPay) {
        this.employee = employee;
        this.totalHours = totalHours;
        this.grossPay = grossPay;
        this.sss = sss;
        this.philHealth = philHealth;
        this.pagibig = pagibig;
        this.withholdingTax = withholdingTax;
        this.totalDeductions = totalDeductions;
        this.netPay = netPay;
    }

    // Getters
    public Employee getEmployee() { return employee; }
    public double getTotalHours() { return totalHours; }
    public double getGrossPay() { return grossPay; }
    public double getSss() { return sss; }
    public double getPhilHealth() { return philHealth; }
    public double getPagibig() { return pagibig; }
    public double getWithholdingTax() { return withholdingTax; }
    public double getTotalDeductions() { return totalDeductions; }
    public double getNetPay() { return netPay; }

    @Override
    public String toString() {
        return String.format(
            "PAYSLIP DETAILS\n" +
            "===============\n" +
            "Employee: %s (%s)\n" +
            "Total Hours: %.2f\n" +
            "Hourly Rate: PHP %.2f\n" +
            "Gross Pay: PHP %.2f\n\n" +
            "DEDUCTIONS:\n" +
            "SSS: PHP %.2f\n" +
            "PhilHealth: PHP %.2f\n" +
            "Pag-IBIG: PHP %.2f\n" +
            "Withholding Tax: PHP %.2f\n" +
            "Total Deductions: PHP %.2f\n\n" +
            "NET PAY: PHP %.2f",
            employee.getFullName(), employee.getEmployeeNumber(),
            totalHours, employee.getHourlyRate(), grossPay,
            sss, philHealth, pagibig, withholdingTax, totalDeductions,
            netPay
        );
    }
}
