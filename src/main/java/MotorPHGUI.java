/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elias
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class MotorPHGUI extends JFrame {

    private JTextField txtEmpNumber, txtStartDate, txtEndDate;
    private JTextArea txtResult;

    public MotorPHGUI() {
        setTitle("MotorPH Employee App");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Payslip Generator"));

        txtEmpNumber = new JTextField();
        txtStartDate = new JTextField();
        txtEndDate = new JTextField();

        inputPanel.add(new JLabel("Employee Number (e.g., EMP001):"));
        inputPanel.add(txtEmpNumber);
        inputPanel.add(new JLabel("Pay Start Date (YYYY-MM-DD):"));
        inputPanel.add(txtStartDate);
        inputPanel.add(new JLabel("Pay End Date (YYYY-MM-DD):"));
        inputPanel.add(txtEndDate);

        JButton btnGenerate = new JButton("Generate Payslip");
        JButton btnExit = new JButton("Exit");

        inputPanel.add(btnGenerate);
        inputPanel.add(btnExit);

        // Result Area
        txtResult = new JTextArea();
        txtResult.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResult);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Result"));

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Button Actions
        btnGenerate.addActionListener(e -> generatePayslip());
        btnExit.addActionListener(e -> System.exit(0));
    }

    private void generatePayslip() {
        String empNum = txtEmpNumber.getText().trim();
        String startDateStr = txtStartDate.getText().trim();
        String endDateStr = txtEndDate.getText().trim();

        // Validate employee number
        if (!empNum.matches("EMP\\d{3}")) {
            showError("Invalid Employee Number. Format must be EMP###.");
            return;
        }

        // Validate dates
        LocalDate startDate, endDate;
        try {
            startDate = LocalDate.parse(startDateStr);
            endDate = LocalDate.parse(endDateStr);
            if (endDate.isBefore(startDate)) {
                showError("End date must not be before start date.");
                return;
            }
        } catch (DateTimeParseException ex) {
            showError("Invalid date format. Use YYYY-MM-DD.");
            return;
        }

        // Create sample employee
        Employee emp = new Employee(empNum, "Cruz", "Juan", "Developer", 200.0);

        // Add sample logs (simulate within date range)
        AttendanceLog.addAttendance(new AttendanceLog(empNum,
                LocalDateTime.of(2025, 5, 14, 9, 0),
                LocalDateTime.of(2025, 5, 14, 17, 0)));

        AttendanceLog.addAttendance(new AttendanceLog(empNum,
                LocalDateTime.of(2025, 5, 15, 9, 30),
                LocalDateTime.of(2025, 5, 15, 18, 0)));

        // Compute payroll
        PayrollSystem payrollSystem = new PayrollSystem();
        double netPay = payrollSystem.generatePayslip(emp);

        // Show result
        txtResult.setText("Payslip for: " + emp.getFullName() + "\n" +
                          "Employee Number: " + emp.getEmployeeNumber() + "\n" +
                          "Net Pay: PHP " + String.format("%.2f", netPay));
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MotorPHGUI app = new MotorPHGUI();
            app.setVisible(true);
        });
    }
}
