package org.project.salesystem.customer.session;

import org.project.salesystem.customer.model.Customer;

/**
 * Manages the current session for a customer.
 * Provides methods to get and set the currently logged-in customer.
 */
public class Session {
    private static Customer currentCustomer;

    /**
     * Gets the currently logged-in customer.
     *
     * @return The current customer, or null if no customer is logged in.
     */
    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    /**
     * Sets the currently logged-in customer.
     *
     * @param customer The customer to set as the current session user.
     */
    public static void setCurrentCustomer(Customer customer) {
        currentCustomer = customer;
    }
}

