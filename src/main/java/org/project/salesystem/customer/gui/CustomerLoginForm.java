package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.controller.CustomerLoginFormListener;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the customer login form GUI.
 * It allows the user to input their username and password to log in.
 */
public class CustomerLoginForm extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    /**
     * Constructor to initialize the login form.
     */
    public CustomerLoginForm() {
        setTitle("Customer Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    /**
     * Returns the text entered in the username field.
     *
     * @return The username entered by the user.
     */
    public String getUsernameText() {
        return usernameField.getText();
    }

    /**
     * Returns the password entered in the password field.
     *
     * @return The password entered by the user as a character array.
     */
    public char[] getPasswordText() {
        return passwordField.getPassword();
    }

    /**
     * Displays a message on the form (e.g., error or success message).
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        messageLabel.setText(message);
    }

    /**
     * Initializes the components of the login form.
     */
    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 15, 15));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);

        JButton logginButton = new JButton("Log In");
        logginButton.addActionListener(new CustomerLoginFormListener(this));

        panel.add(new JLabel("Username:", SwingConstants.CENTER));
        panel.add(usernameField);
        panel.add(new JLabel("Password:", SwingConstants.CENTER));
        panel.add(passwordField);

        add(messageLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(logginButton, BorderLayout.SOUTH);
    }
}
