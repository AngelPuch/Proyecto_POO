package org.project.salesystem.admin.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testDefaultConstructor() {
        Product product = new Product();

        assertEquals(0, product.getId());
        assertNull(product.getName());
        assertEquals(0.0, product.getPrice());
        assertEquals(0, product.getStock());
        assertNull(product.getCategory());
        assertNull(product.getSupplier());
    }

    @Test
    void testConstructorWithParameters() {
        Category category = new Category(1, "Category juego", "descripción categoría");
        Supplier supplier = new Supplier(1, "Proveedor juego", "797890");

        Product product = new Product(1, "juego1", 45.6, 2, category, supplier);

        assertEquals(1, product.getId());
        assertEquals("juego1", product.getName());
        assertEquals(45.6, product.getPrice());
        assertEquals(2, product.getStock());
        assertEquals(category, product.getCategory());
        assertEquals(supplier, product.getSupplier());
    }

}