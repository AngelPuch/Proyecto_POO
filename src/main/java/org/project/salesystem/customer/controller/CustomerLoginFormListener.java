package org.project.salesystem.customer.controller;

import org.project.salesystem.customer.dao.CustomerDAO;
import org.project.salesystem.customer.dao.implementation.CustomerDAOImpl;
import org.project.salesystem.customer.gui.CustomerLoginForm;
import static org.project.salesystem.customer.gui.TabbedPaneCustomer.openTabbedPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerLoginFormListener implements ActionListener {
    private final CustomerLoginForm customerLoginForm;

    public CustomerLoginFormListener(CustomerLoginForm customerLoginForm) {
        this.customerLoginForm = customerLoginForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = customerLoginForm.getUsernameText();
        char[] password = customerLoginForm.getPasswordText();

        if (authenticate(username, password)) {
            JOptionPane.showMessageDialog(customerLoginForm, "Bienvenido, " + username);
            customerLoginForm.dispose();
            openTabbedPane();
        } else {
            customerLoginForm.showMessage("Usuario o contraseña incorrectos");
        }
    }

    private boolean authenticate(String username, char[] password) {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.findCustomerByUsernameAndPassword(username, new String(password)) != null;
    }
}

