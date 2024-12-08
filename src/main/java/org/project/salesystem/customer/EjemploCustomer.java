package org.project.salesystem.customer;

import org.project.salesystem.customer.gui.MainCustomerWindow;

import javax.swing.*;

/**
 * Entry point for the Customer module of the Sales System.
 * Initializes and displays the main window for the customer interface.
 */
public class EjemploCustomer {

    /**
     * Main method to launch the customer application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure the UI updates are executed on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainCustomerWindow mainCustomerWindow = new MainCustomerWindow();
            mainCustomerWindow.setVisible(true);
        });
    }
}

