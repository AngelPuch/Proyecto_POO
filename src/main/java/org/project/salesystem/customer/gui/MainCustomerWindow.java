package org.project.salesystem.customer.gui;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the main window of the customer system, offering options to either log in or register.
 */
public class MainCustomerWindow extends JFrame {

    /**
     * Constructor that sets up the main window for the customer system.
     */
    public MainCustomerWindow() {
        setTitle("Customer System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    /**
     * Initializes the components of the main window, including buttons for login and registration.
     */
    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 20, 20));
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.addActionListener(e -> openLoginForm());
        registerButton.addActionListener(e -> openRegisterForm());

        panel.add(loginButton);
        panel.add(registerButton);
        add(panel);
    }

    /**
     * Opens the customer login form and disposes of the main window.
     */
    private void openLoginForm() {
        new CustomerLoginForm().setVisible(true);
        this.dispose();
    }

    /**
     * Opens the customer registration form and disposes of the main window.
     */
    private void openRegisterForm() {
        new CustomerRegisterForm().setVisible(true);
        this.dispose();
    }
}

