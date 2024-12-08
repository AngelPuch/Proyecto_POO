package org.project.salesystem.customer.controller;

import org.project.salesystem.customer.dao.CustomerDAO;
import org.project.salesystem.customer.dao.implementation.CustomerDAOImpl;
import org.project.salesystem.customer.gui.CustomerLoginForm;
import org.project.salesystem.customer.model.Customer;
import org.project.salesystem.customer.session.Session;

import static org.project.salesystem.customer.gui.TabbedPaneCustomer.openTabbedPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class listens to the login actions in the customer login form.
 * It authenticates the user by verifying their username and password.
 * If the authentication is successful, it opens the main application window.
 * Otherwise, it shows an error message to the user.
 */
public class CustomerLoginFormListener implements ActionListener {

    private final CustomerLoginForm customerLoginForm;

    /**
     * Constructor that initializes the listener with the login form.
     *
     * @param customerLoginForm The login form that this listener will monitor.
     */
    public CustomerLoginFormListener(CustomerLoginForm customerLoginForm) {
        this.customerLoginForm = customerLoginForm;
    }

    /**
     * This method is invoked when the login button is pressed.
     * It validates the username and password entered by the user.
     * If the credentials are correct, the user is authenticated and logged in.
     * If not, an error message is displayed.
     *
     * @param e The action event triggered by the login button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = customerLoginForm.getUsernameText();
        char[] password = customerLoginForm.getPasswordText();

        if (authenticate(username, password)) {
            JOptionPane.showMessageDialog(customerLoginForm, "Welcome, " + username);
            CustomerDAO customerDAO = new CustomerDAOImpl();
            Customer customer = customerDAO.findCustomerByUsernameAndPassword(username, new String(password));
            if (customer != null) {
                Session.setCurrentCustomer(customer);
                customerLoginForm.dispose();
                openTabbedPane();
            } else {
                customerLoginForm.showMessage("Invalid username or password.");
            }
        } else {
            customerLoginForm.showMessage("Invalid username or password.");
        }
    }

    /**
     * Authenticates the user by checking the provided username and password.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return true if the username and password are correct, false otherwise.
     */
    private boolean authenticate(String username, char[] password) {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.findCustomerByUsernameAndPassword(username, new String(password)) != null;
    }
}
