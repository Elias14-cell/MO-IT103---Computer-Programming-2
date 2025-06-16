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

public class LoginDialog extends JDialog {
    private final JTextField txtUsername;
    private JPasswordField txtPassword;
    private boolean authenticated = false;
    private PopupMenu btnLogin;
    
    public LoginDialog(JFrame parent) {
        super(parent, "Login", true);
        setLayout(new GridLayout(3, 2));
        
        add(new JLabel("Username:"));
        txtUsername = new JTextField();
        add(txtUsername);
        
        add(new JLabel("Password"));
        btnLogin.addActionListener(e -> authenticateUser());
        add(btnLogin);
        
        setSize(300, 150);
        setLocationRelativeTo(parent);
    }
    
    private void authenticateUser() {
        if ("admin".equals(txtUsername.getText()) && "password" .equals(new String(txtPassword.getPassword()))) {
            authenticated = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean isAuthenticated() {
        return authenticated;
    }
    
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}
