package org.project.salesystem.admin.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {

    @Test
    void testDefaultConstructor() {
        Supplier supplier = new Supplier();

        assertEquals(0, supplier.getId());
        assertNull(supplier.getName());
        assertNull(supplier.getPhone());
    }

    @Test
    void testConstructorWithParameters() {

        Supplier supplier = new Supplier(1, "Proveedor de juegos", "124752");

        assertEquals(1, supplier.getId());
        assertEquals("Proveedor de juegos", supplier.getName());
        assertEquals("124752", supplier.getPhone());
    }

    @Test
    void testSettersAndGetters() {
        Supplier supplier = new Supplier();

        supplier.setId(2);
        supplier.setName("Proveedor nuevo");
        supplier.setPhone("46874879");

        assertEquals(2, supplier.getId());
        assertEquals("Proveedor nuevo", supplier.getName());
        assertEquals("46874879", supplier.getPhone());
    }

    @Test
    void testToString() {
        Supplier supplier = new Supplier(3, "Proveedor", "12345689");

        assertEquals("Proveedor", supplier.toString());
    }

}