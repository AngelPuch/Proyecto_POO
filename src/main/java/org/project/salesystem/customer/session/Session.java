package org.project.salesystem.customer.session;

import org.project.salesystem.customer.model.Customer;

public class Session {
    private static Customer currentCustomer;

    // Getter para obtener el cliente actual
    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    // Setter para establecer el cliente actual
    public static void setCurrentCustomer(Customer customer) {
        currentCustomer = customer;
    }

    // Metodo para verificar si hay un cliente logueado
    public static boolean isCustomerLoggedIn() {
        return currentCustomer != null;
    }
}
