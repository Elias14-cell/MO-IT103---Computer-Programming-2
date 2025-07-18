/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private boolean authenticated = false;
    
    // Sample user credentials (in real application, this would be from a database)
    private static final Map<String, String> validUsers = new HashMap<>();
    
    static {
        validUsers.put("admin", "admin123");
        validUsers.put("hr", "hr123");
        validUsers.put("manager", "manager123");
    }
    
    public LoginDialog(Frame parent) {
        super(parent, "MotorPH Login", true);
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(parent);
        setResizable(false);
    }
    
    private void initializeComponents() {
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        
        // Set default button
        getRootPane().setDefaultButton(loginButton);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(41, 128, 185));
        JLabel titleLabel = new JLabel("MotorPH Employee Management System");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titlePanel.add(titleLabel);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Username
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(usernameField, gbc);
        
        // Password
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(passwordField, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        
        // Add panels to dialog
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void setupEventHandlers() {
        loginButton.addActionListener((ActionEvent e) -> {
            performLogin();
        });
        
        cancelButton.addActionListener((ActionEvent e) -> {
            authenticated = false;
            dispose();
        });
        
        // Allow Enter key to trigger login
        KeyListener enterKeyListener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {}
            
            @Override
            public void keyTyped(KeyEvent e) {}
        };
        
        usernameField.addKeyListener(enterKeyListener);
        passwordField.addKeyListener(enterKeyListener);
    }
    
    private void performLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter both username and password.");
            return;
        }
        
        if (validateCredentials(username, password)) {
            authenticated = true;
            dispose();
        } else {
            showError("Invalid username or password.");
            passwordField.setText("");
            usernameField.requestFocus();
        }
    }
    
    private boolean validateCredentials(String username, String password) {
        return validUsers.containsKey(username) && validUsers.get(username).equals(password);
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public boolean isAuthenticated() {
        return authenticated;
    }
    
    // Method to add new users (for future use)
    public static void addUser(String username, String password) {
        validUsers.put(username, password);
    }
    
    // Method to display available login credentials (for demo purposes)
    public static void showLoginCredentials() {
        StringBuilder sb = new StringBuilder();
        sb.append("Available Login Credentials:\n");
        sb.append("===========================\n");
        for (Map.Entry<String, String> entry : validUsers.entrySet()) {
            sb.append("Username: ").append(entry.getKey())
              .append(" | Password: ").append(entry.getValue()).append("\n");
        }
        
        JOptionPane.showMessageDialog(null, sb.toString(), "Login Credentials", 
                                    JOptionPane.INFORMATION_MESSAGE);
    }
}