package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.dao.CustomerDAO;
import org.project.salesystem.customer.dao.implementation.CustomerDAOImpl;
import org.project.salesystem.customer.model.Customer;

import javax.swing.*;
import java.awt.*;

public class CustomerRegisterForm extends JFrame{
    private JTextField nameField, phoneField, usernameFlied;
    private JPasswordField passwordField;

    public CustomerRegisterForm(){
        setTitle("Registro de Cliente");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents(){
            JPanel panel = new JPanel(new GridLayout(8,2,10,10));

            panel.add(new JLabel("Nombre:"));
            nameField = new JTextField();
            panel.add(nameField);

            panel.add(new JLabel("Usuario"));
            usernameFlied = new JTextField();
            panel.add(usernameFlied);

            panel.add(new JLabel("Contraseña:"));
            passwordField = new JPasswordField();
            panel.add(passwordField);

            panel.add(new JLabel("Telefono:"));
            phoneField = new JTextField();
            panel.add(phoneField);


            JButton registerButton = new JButton("Registrarse");
            registerButton.addActionListener(e -> registerCustomer());

            panel.add(new JLabel());
            panel.add(registerButton);
            add(panel, BorderLayout.CENTER);
        }

        private void registerCustomer(){
        Customer customer = new Customer();
        customer.setName(nameField.getText());
        customer.setPhoneNumber(phoneField.getText());
        customer.setUsername(usernameFlied.getText());
        customer.setPassword(new String(passwordField.getPassword()));

        CustomerDAO customerDAO = new CustomerDAOImpl();
        customerDAO.create(customer);

        JOptionPane.showMessageDialog(this, "Cliente registrado exitosamente!");
        this.dispose();
        new MainCustomerWindow().setVisible(true);
        }
}
