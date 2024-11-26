package org.project.salesystem.customer.model;

import org.project.salesystem.customer.gui.MainCustomerWindow;

import javax.swing.*;

public class EjemploCustomer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            MainCustomerWindow mainCustomerWindow = new MainCustomerWindow();
            mainCustomerWindow.setVisible(true);
        });
    }
}
