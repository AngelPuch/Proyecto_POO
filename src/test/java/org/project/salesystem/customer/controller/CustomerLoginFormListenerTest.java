package org.project.salesystem.customer.controller;

import org.junit.jupiter.api.Test;
import org.project.salesystem.customer.controller.CustomerLoginFormListener;
import org.project.salesystem.customer.gui.CustomerLoginForm;

import static org.junit.jupiter.api.Assertions.*;

class CustomerLoginFormListenerTest {
    // El metodo authenticate lo volví público para que se puedan hacer las pruebas

    @Test
    void testAuthenticateSucces() {
        CustomerLoginFormListener customerLoginFormListener = new CustomerLoginFormListener(new CustomerLoginForm());
        assertTrue(customerLoginFormListener.authenticate("josel", "12345".toCharArray()));
    }

    @Test
    void testAuthenticateFailure() {
        CustomerLoginFormListener customerLoginFormListener = new CustomerLoginFormListener(new CustomerLoginForm());
        assertFalse(customerLoginFormListener.authenticate("joseluis", "15".toCharArray()));
    }
}
