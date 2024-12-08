package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.dao.CustomerDAO;
import org.project.salesystem.customer.dao.implementation.CustomerDAOImpl;
import org.project.salesystem.customer.model.Customer;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the customer registration form where a new customer can enter their details
 * to register an account in the system.
 */
public class CustomerRegisterForm extends JFrame {

    private JTextField nameField, phoneField, usernameFlied, streetField, postal_codeField, cityField, stateField;
    private JPasswordField passwordField;

    /**
     * Constructor that sets up the registration form.
     */
    public CustomerRegisterForm() {
        setTitle("Customer Registration");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    /**
     * Initializes the components of the registration form.
     */
    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(10, 2, 15, 15));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Username"));
        usernameFlied = new JTextField();
        panel.add(usernameFlied);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("Street:"));
        streetField = new JTextField();
        panel.add(streetField);

        panel.add(new JLabel("Postal Code:"));
        postal_codeField = new JTextField();
        panel.add(postal_codeField);

        panel.add(new JLabel("City:"));
        cityField = new JTextField();
        panel.add(cityField);

        panel.add(new JLabel("State:"));
        stateField = new JTextField();
        panel.add(stateField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> registerCustomer());

        panel.add(new JLabel());
        panel.add(registerButton);
        add(panel, BorderLayout.CENTER);
    }

    /**
     * Registers the new customer by collecting the data from the form and saving it in the database.
     * After successful registration, it shows a confirmation message and closes the registration form.
     */
    private void registerCustomer() {
        Customer customer = new Customer();
        customer.setName(nameField.getText());
        customer.setPhoneNumber(phoneField.getText());
        customer.setUsername(usernameFlied.getText());
        customer.setPassword(new String(passwordField.getPassword()));
        customer.setStreet(streetField.getText());
        customer.setPostal_code(postal_codeField.getText());
        customer.setCity(cityField.getText());
        customer.setState(stateField.getText());

        CustomerDAO customerDAO = new CustomerDAOImpl();
        customerDAO.create(customer);

        JOptionPane.showMessageDialog(this, "Customer registered successfully!");
        this.dispose();
        new MainCustomerWindow().setVisible(true);
    }
}
