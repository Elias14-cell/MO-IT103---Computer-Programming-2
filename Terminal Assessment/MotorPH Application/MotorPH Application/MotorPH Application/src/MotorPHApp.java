/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

// Main GUI Application
public class MotorPHApp extends JFrame {
    private JTabbedPane tabbedPane;
    private DefaultTableModel employeeTableModel;
    private JTable employeeTable;
    private final Map<String, Employee> employees;
    private final PayrollSystem payrollSystem;
    
    // Employee form fields
    private JTextField txtEmpNumber, txtFirstName, txtLastName, txtBirthday;
    private JTextField txtSSS, txtPhilHealth, txtTIN, txtPagibig, txtHourlyRate;
    
    // Payroll fields
    private JTextField txtPayrollEmpNumber, txtStartDate, txtEndDate;
    private JTextArea txtPayrollResult;

    public MotorPHApp() {
        employees = new HashMap<>();
        payrollSystem = new PayrollSystem();
        
        // Show login dialog first
        if (!showLoginDialog()) {
            System.exit(0); // Exit if login failed
        }
        
        initializeGUI();
        loadSampleData();
    }

    private boolean showLoginDialog() {
        LoginDialog loginDialog = new LoginDialog(this);
        loginDialog.setVisible(true);
         return loginDialog.isAuthenticated();
    }
    private void initializeGUI() {
        setTitle("MotorPH Employee Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        
        // Tab 1: Employee Management
        tabbedPane.addTab("Employee Management", createEmployeeManagementPanel());
        
        // Tab 2: Payroll System
        tabbedPane.addTab("Payroll System", createPayrollPanel());

        add(tabbedPane);
    }

    private JPanel createEmployeeManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Employee Table
        String[] columns = {"Employee Number", "Last Name", "First Name", "SSS Number", 
                           "PhilHealth Number", "TIN", "Pag-IBIG Number"};
        employeeTableModel = new DefaultTableModel(columns, 0);
        employeeTable = new JTable(employeeTableModel);
        employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadSelectedEmployeeData();
            }
        });

        JScrollPane tableScrollPane = new JScrollPane(employeeTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Employee Records"));

        // Employee Form Panel
        JPanel formPanel = createEmployeeFormPanel();

        // Button Panel
        JPanel buttonPanel = createEmployeeButtonPanel();

        // Layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(tableScrollPane, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createEmployeeFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Employee Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Initialize text fields
        txtEmpNumber = new JTextField(15);
        txtFirstName = new JTextField(15);
        txtLastName = new JTextField(15);
        txtBirthday = new JTextField(15);
        txtSSS = new JTextField(15);
        txtPhilHealth = new JTextField(15);
        txtTIN = new JTextField(15);
        txtPagibig = new JTextField(15);
        txtHourlyRate = new JTextField(15);

        // Add components to form
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Employee Number:"), gbc);
        gbc.gridx = 1;
        panel.add(txtEmpNumber, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 3;
        panel.add(txtFirstName, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        panel.add(txtLastName, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Birthday:"), gbc);
        gbc.gridx = 3;
        panel.add(txtBirthday, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("SSS Number:"), gbc);
        gbc.gridx = 1;
        panel.add(txtSSS, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("PhilHealth Number:"), gbc);
        gbc.gridx = 3;
        panel.add(txtPhilHealth, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("TIN:"), gbc);
        gbc.gridx = 1;
        panel.add(txtTIN, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Pag-IBIG Number:"), gbc);
        gbc.gridx = 3;
        panel.add(txtPagibig, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Hourly Rate:"), gbc);
        gbc.gridx = 1;
        panel.add(txtHourlyRate, gbc);

        return panel;
    }

    private JPanel createEmployeeButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton btnNew = new JButton("New Employee");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnViewEmployee = new JButton("View Employee");
        JButton btnClear = new JButton("Clear Form");

        btnNew.addActionListener(e -> addNewEmployee());
        btnUpdate.addActionListener(e -> updateEmployee());
        btnDelete.addActionListener(e -> deleteEmployee());
        btnViewEmployee.addActionListener(e -> viewEmployeeDetails());
        btnClear.addActionListener(e -> clearEmployeeForm());

        panel.add(btnNew);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnViewEmployee);
        panel.add(btnClear);

        return panel;
    }

    private JPanel createPayrollPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Payroll Calculator"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        txtPayrollEmpNumber = new JTextField(15);
        txtStartDate = new JTextField("2025-05-14", 15);
        txtEndDate = new JTextField("2025-05-15", 15);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(new JLabel("Employee Number:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(txtPayrollEmpNumber, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Pay Start Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        inputPanel.add(txtStartDate, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Pay End Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        inputPanel.add(txtEndDate, gbc);

        JButton btnGenerate = new JButton("Generate Payslip");
        JButton btnClearPayroll = new JButton("Clear");

        gbc.gridx = 0; gbc.gridy = 3;
        inputPanel.add(btnGenerate, gbc);
        gbc.gridx = 1;
        inputPanel.add(btnClearPayroll, gbc);

        btnGenerate.addActionListener(e -> generatePayslip());
        btnClearPayroll.addActionListener(e -> clearPayrollForm());

        // Result Area
        txtPayrollResult = new JTextArea();
        txtPayrollResult.setEditable(false);
        txtPayrollResult.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtPayrollResult);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Payslip Result"));

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void loadSampleData() {
        // Add sample employees
        Employee emp1 = new Employee("EMP001", "Juan", "Cruz", "1990-01-01", 
                                   "123456789", "PH001", "TIN001", "PAG001", 250.0);
        Employee emp2 = new Employee("EMP002", "Maria", "Santos", "1985-05-15", 
                                   "987654321", "PH002", "TIN002", "PAG002", 300.0);
        Employee emp3 = new Employee("EMP003", "Jose", "Rizal", "1988-12-30", 
                                   "456789123", "PH003", "TIN003", "PAG003", 280.0);

        employees.put(emp1.getEmpNumber(), emp1);
        employees.put(emp2.getEmpNumber(), emp2);
        employees.put(emp3.getEmpNumber(), emp3);

        refreshEmployeeTable();
    }

    private void refreshEmployeeTable() {
        employeeTableModel.setRowCount(0);
        for (Employee emp : employees.values()) {
            Object[] row = {
                emp.getEmpNumber(),
                emp.getLastName(),
                emp.getFirstName(),
                emp.getSssNumber(),
                emp.getPhilHealthNumber(),
                emp.getTin(),
                emp.getPagibigNumber()
            };
            employeeTableModel.addRow(row);
        }
    }

    private void loadSelectedEmployeeData() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String empNumber = (String) employeeTableModel.getValueAt(selectedRow, 0);
            Employee emp = employees.get(empNumber);
            if (emp != null) {
                txtEmpNumber.setText(emp.getEmpNumber());
                txtFirstName.setText(emp.getFirstName());
                txtLastName.setText(emp.getLastName());
                txtBirthday.setText(emp.getBirthday());
                txtSSS.setText(emp.getSssNumber());
                txtPhilHealth.setText(emp.getPhilHealthNumber());
                txtTIN.setText(emp.getTin());
                txtPagibig.setText(emp.getPagibigNumber());
                txtHourlyRate.setText(String.valueOf(emp.getHourlyRate()));
            }
        }
    }

    private void addNewEmployee() {
        try {
            validateEmployeeInput();
            
            String empNumber = txtEmpNumber.getText().trim();
            if (employees.containsKey(empNumber)) {
                showError("Employee number already exists!");
                return;
            }

            Employee newEmp = new Employee(
                empNumber,
                txtFirstName.getText().trim(),
                txtLastName.getText().trim(),
                txtBirthday.getText().trim(),
                txtSSS.getText().trim(),
                txtPhilHealth.getText().trim(),
                txtTIN.getText().trim(),
                txtPagibig.getText().trim(),
                Double.parseDouble(txtHourlyRate.getText().trim())
            );

            employees.put(empNumber, newEmp);
            refreshEmployeeTable();
            clearEmployeeForm();
            showMessage("Employee added successfully!");

        } catch (Exception e) {
            showError("Error adding employee: " + e.getMessage());
        }
    }

    private void updateEmployee() {
        try {
            String empNumber = txtEmpNumber.getText().trim();
            if (empNumber.isEmpty()) {
                showError("Please select an employee to update.");
                return;
            }

            Employee emp = employees.get(empNumber);
            if (emp == null) {
                showError("Employee not found!");
                return;
            }

            validateEmployeeInput();

            emp.setFirstName(txtFirstName.getText().trim());
            emp.setLastName(txtLastName.getText().trim());
            emp.setBirthday(txtBirthday.getText().trim());
            emp.setSssNumber(txtSSS.getText().trim());
            emp.setPhilHealthNumber(txtPhilHealth.getText().trim());
            emp.setTin(txtTIN.getText().trim());
            emp.setPagibigNumber(txtPagibig.getText().trim());
            emp.setHourlyRate(Double.parseDouble(txtHourlyRate.getText().trim()));

            refreshEmployeeTable();
            showMessage("Employee updated successfully!");

        } catch (Exception e) {
            showError("Error updating employee: " + e.getMessage());
        }
    }

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow < 0) {
            showError("Please select an employee to delete.");
            return;
        }

        String empNumber = (String) employeeTableModel.getValueAt(selectedRow, 0);
        
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete employee " + empNumber + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            employees.remove(empNumber);
            refreshEmployeeTable();
            clearEmployeeForm();
            showMessage("Employee deleted successfully!");
        }
    }

    private void viewEmployeeDetails() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow < 0) {
            showError("Please select an employee to view.");
            return;
        }

        String empNumber = (String) employeeTableModel.getValueAt(selectedRow, 0);
        Employee emp = employees.get(empNumber);
        
        if (emp != null) {
            String details = String.format("""
                                           EMPLOYEE DETAILS
                                           ================
                                           Employee Number: %s
                                           Name: %s
                                           Birthday: %s
                                           SSS Number: %s
                                           PhilHealth Number: %s
                                           TIN: %s
                                           Pag-IBIG Number: %s
                                           Hourly Rate: PHP %.2f""",
                emp.getEmpNumber(),
                emp.getFullName(),
                emp.getBirthday(),
                emp.getSssNumber(),
                emp.getPhilHealthNumber(),
                emp.getTin(),
                emp.getPagibigNumber(),
                emp.getHourlyRate()
            );

            JOptionPane.showMessageDialog(this, details, "Employee Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void generatePayslip() {
        String empNum = txtPayrollEmpNumber.getText().trim();
        String startDateStr = txtStartDate.getText().trim();
        String endDateStr = txtEndDate.getText().trim();

        try {
            // Validate employee number
            if (!empNum.matches("EMP\\d{3}")) {
                showError("Invalid Employee Number format. Use EMP### (e.g., EMP001)");
                return;
            }

            // Check if employee exists
            Employee emp = employees.get(empNum);
            if (emp == null) {
                showError("Employee not found!");
                return;
            }

            // Validate dates
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);
            
            if (endDate.isBefore(startDate)) {
                showError("End date must not be before start date.");
                return;
            }

            // Clear previous attendance logs
            AttendanceLog.clearAllLogs();

            // Generate sample attendance for the date range
            LocalDate currentDate = startDate;
            while (!currentDate.isAfter(endDate)) {
                
                if (currentDate.getDayOfWeek().getValue() < 6) {
                    AttendanceLog.addAttendance(new AttendanceLog(empNum,
                            LocalDateTime.of(currentDate, java.time.LocalTime.of(9, 0)),
                            LocalDateTime.of(currentDate, java.time.LocalTime.of(17, 30))));
                }
                currentDate = currentDate.plusDays(1);
            }

            // Generate payslip
            PayslipDetails payslip = payrollSystem.generateDetailedPayslip(emp);
            txtPayrollResult.setText(payslip.toString());

        } catch (DateTimeParseException e) {
            showError("Invalid date format. Use YYYY-MM-DD format.");
        } catch (Exception e) {
            showError("Error generating payslip: " + e.getMessage());
        }
    }

    private void validateEmployeeInput() throws Exception {
        if (txtEmpNumber.getText().trim().isEmpty()) {
            throw new Exception("Employee number is required");
        }
        if (!txtEmpNumber.getText().trim().matches("EMP\\d{3}")) {
            throw new Exception("Employee number must be in format EMP### (e.g., EMP001)");
        }
        if (txtFirstName.getText().trim().isEmpty()) {
            throw new Exception("First name is required");
        }
        if (txtLastName.getText().trim().isEmpty()) {
            throw new Exception("Last name is required");
        }
        if (txtHourlyRate.getText().trim().isEmpty()) {
            throw new Exception("Hourly rate is required");
        }
        
        try {
            double rate = Double.parseDouble(txtHourlyRate.getText().trim());
            if (rate <= 0) {
                throw new Exception("Hourly rate must be greater than 0");
            }
        } catch (NumberFormatException e) {
            throw new Exception("Invalid hourly rate format");
        }
    }

    private void clearEmployeeForm() {
        txtEmpNumber.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtBirthday.setText("");
        txtSSS.setText("");
        txtPhilHealth.setText("");
        txtTIN.setText("");
        txtPagibig.setText("");
        txtHourlyRate.setText("");
        employeeTable.clearSelection();
    }

    private void clearPayrollForm() {
        txtPayrollEmpNumber.setText("");
        txtPayrollResult.setText("");
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
          
        }
        
        SwingUtilities.invokeLater(() -> {
            new MotorPHApp().setVisible(true);
        });
    }
}
