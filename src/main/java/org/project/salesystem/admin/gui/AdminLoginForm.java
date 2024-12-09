package org.project.salesystem.admin.gui;

import org.project.salesystem.admin.controller.AdminLoginFormListener;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the login form for administrators.
 * Provides fields for entering username and password and a button to submit the login.
 */
public class AdminLoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public AdminLoginForm(){
        setTitle("Inicio de sesión Administrador");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    public String getUsernameText() { return usernameField.getText(); }
    public char[] getPasswordText() { return passwordField.getPassword(); }

    public void showMessage(String message) {
        messageLabel.setText(message);
    }

    /**
     * Initializes and arranges the components of the login form.
     */
    private void initComponents (){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 15, 15));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.red);

        panel.add(new JLabel("Username: ", SwingConstants.CENTER));
        panel.add(usernameField);
        panel.add(new JLabel("Password: ", SwingConstants.CENTER));
        panel.add(passwordField);

        JButton logginButton = new JButton("Iniciar Sesión");
        logginButton.addActionListener(new AdminLoginFormListener(this));

        add(messageLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(logginButton, BorderLayout.SOUTH);
    }
}
