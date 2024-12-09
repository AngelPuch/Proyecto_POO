package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.controller.CustomerRegisterFormController;
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
    CustomerRegisterFormController controller;

    /**
     * Constructor that sets up the registration form.
     */
    public CustomerRegisterForm() {
        setTitle("Customer Registration");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        this.controller = new CustomerRegisterFormController(this);
    }

    public JTextField getNameField() { return nameField; }
    public JTextField getPhoneField() { return phoneField; }
    public JTextField getUsernameFlied() { return usernameFlied; }
    public JTextField getStreetField() { return streetField; }
    public JTextField getPostal_codeField() { return postal_codeField; }
    public JTextField getCityField() { return cityField; }
    public JTextField getStateField() { return stateField; }
    public JPasswordField getPasswordField() { return passwordField; }

    /**
     * Initializes the components of the registration form.
     */
    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(10, 2, 15, 15));

        panel.add(new JLabel("Nombre:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Username"));
        usernameFlied = new JTextField();
        panel.add(usernameFlied);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel("Número de teléfono:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("Calle y número:"));
        streetField = new JTextField();
        panel.add(streetField);

        panel.add(new JLabel("Código postal:"));
        postal_codeField = new JTextField();
        panel.add(postal_codeField);

        panel.add(new JLabel("Ciudad:"));
        cityField = new JTextField();
        panel.add(cityField);

        panel.add(new JLabel("Estado:"));
        stateField = new JTextField();
        panel.add(stateField);

        JButton registerButton = new JButton("Registrarme");
        registerButton.addActionListener(e -> controller.registerCustomer());

        panel.add(new JLabel());
        panel.add(registerButton);
        add(panel, BorderLayout.CENTER);
    }


}
