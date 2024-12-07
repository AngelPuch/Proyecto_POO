package org.project.salesystem.customer.session;

import org.project.salesystem.customer.model.Customer;

public class Session {
    private static Customer currentCustomer;

    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public static void setCurrentCustomer(Customer customer) {
        if (customer == null) {
            System.err.println("Intento de establecer una sesión con cliente nulo.");
        } else {
            System.out.println("Estableciendo cliente en sesión: " + customer.getUsername());
        }
        currentCustomer = customer;
    }
}
