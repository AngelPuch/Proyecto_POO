package org.project.salesystem.admin.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    @Test
    void testDefaultConstructor() {
        Admin admin = new Admin();

        assertEquals(0, admin.getId());
        assertNull(admin.getUsername());
        assertNull(admin.getPassword());
    }

    @Test
    void testConstructorWithParameters() {
        Admin admin = new Admin(1, "adminUser", "123");

        assertEquals(1, admin.getId());
        assertEquals("adminUser", admin.getUsername());
        assertEquals("123", admin.getPassword());
    }

    @Test
    void testSettersAndGetters() {
        Admin admin = new Admin();

        admin.setId(2);
        admin.setUsername("newAdmin");
        admin.setPassword("12345");

        assertEquals(2, admin.getId());
        assertEquals("newAdmin", admin.getUsername());
        assertEquals("12345", admin.getPassword());
    }

}