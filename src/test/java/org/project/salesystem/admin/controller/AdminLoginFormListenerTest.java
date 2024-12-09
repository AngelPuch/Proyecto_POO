package org.project.salesystem.admin.controller;

import org.junit.jupiter.api.Test;
import org.project.salesystem.admin.gui.AdminLoginForm;

import static org.junit.jupiter.api.Assertions.*;

class AdminLoginFormListenerTest {
    // El metodo authenticate lo volví público para que se puedan hacer las pruebas

    @Test
    void testAuthenticateSucces() {
        AdminLoginFormListener adminLoginFormListener = new AdminLoginFormListener(new AdminLoginForm());

        assertTrue(adminLoginFormListener.authenticate("administrador", "12345".toCharArray()));
    }

    @Test
    void testAuthenticateFailure() {
        AdminLoginFormListener adminLoginFormListener = new AdminLoginFormListener(new AdminLoginForm());

        assertFalse(adminLoginFormListener.authenticate("admin", "12345".toCharArray()));
    }
}