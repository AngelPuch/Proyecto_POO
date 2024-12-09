package org.project.salesystem.customer.controller;

import org.project.salesystem.customer.dao.CustomerDAO;
import org.project.salesystem.customer.dao.implementation.CustomerDAOImpl;
import org.project.salesystem.customer.gui.CustomerRegisterForm;
import org.project.salesystem.customer.gui.MainCustomerWindow;
import org.project.salesystem.customer.model.Customer;

import javax.swing.*;

public class CustomerRegisterFormController {
    CustomerRegisterForm customerRegisterForm;

    public CustomerRegisterFormController(CustomerRegisterForm customerRegisterForm) {
        this.customerRegisterForm = customerRegisterForm;
    }

    public void registerCustomer() {
        if (validateNonEmptyField()) {
            if (isValidPhoneNumber()) {
                Customer customer = createCustomer();
                CustomerDAO customerDAO = new CustomerDAOImpl();
                customerDAO.create(customer);

                JOptionPane.showMessageDialog(customerRegisterForm, "Cliente registrado exitosamente!");
                customerRegisterForm.dispose();
                new MainCustomerWindow().setVisible(true);
            }
        }else {
            JOptionPane.showMessageDialog(customerRegisterForm, "Todos los campos son obligatorios.");
        }

    }

    public Customer createCustomer() {
        Customer customer = new Customer();
        customer.setName(customerRegisterForm.getNameField().getText());
        customer.setPhoneNumber(customerRegisterForm.getPhoneField().getText());
        customer.setUsername(customerRegisterForm.getUsernameFlied().getText());
        customer.setPassword(new String(customerRegisterForm.getPasswordField().getPassword()));
        customer.setStreet(customerRegisterForm.getStreetField().getText());
        customer.setPostal_code(customerRegisterForm.getPostal_codeField().getText());
        customer.setCity(customerRegisterForm.getCityField().getText());
        customer.setState(customerRegisterForm.getStateField().getText());
        return customer;
    }

    private boolean validateNonEmptyField() {
        return !customerRegisterForm.getNameField().getText().trim().isEmpty() &&
                !customerRegisterForm.getPhoneField().getText().trim().isEmpty() &&
                !customerRegisterForm.getUsernameFlied().getText().trim().isEmpty() &&
                customerRegisterForm.getPasswordField().getPassword().length > 0 &&
                !customerRegisterForm.getStreetField().getText().trim().isEmpty() &&
                !customerRegisterForm.getPostal_codeField().getText().trim().isEmpty() &&
                !customerRegisterForm.getCityField().getText().trim().isEmpty() &&
                !customerRegisterForm.getStateField().getText().trim().isEmpty();
    }

    /**
     * Validates if the entered phone number is valid.
     * @return {@code true} if the phone number is valid (10 digits and numeric),
     *         {@code false} otherwise.
     */
    private boolean isValidPhoneNumber() {
        String phoneNumber = customerRegisterForm.getPhoneField().getText();
        if (phoneNumber.length() != 10 || !phoneNumber.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(customerRegisterForm, "Debe ingresar un número de teléfono valido");
            return false;
        }
        return true;
    }


}
