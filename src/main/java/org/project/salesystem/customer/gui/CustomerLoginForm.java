package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.controller.CustomerLoginFormListener;

import javax.swing.*;
import java.awt.*;

public class CustomerLoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public CustomerLoginForm() {
        setTitle("Inicio de Sesión Cliente");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    public String getUsernameText() { return usernameField.getText(); }
    public char[] getPasswordText() { return passwordField.getPassword(); }
    public void showMessage(String message) { messageLabel.setText(message); }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 15, 15));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);

        JButton logginButton = new JButton("Iniciar Sesión");
        logginButton.addActionListener(new CustomerLoginFormListener(this));

        panel.add(new JLabel("Usuario:", SwingConstants.CENTER));
        panel.add(usernameField);
        panel.add(new JLabel("Contraseña:", SwingConstants.CENTER));
        panel.add(passwordField);

        add(messageLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(logginButton, BorderLayout.SOUTH);
    }

}

