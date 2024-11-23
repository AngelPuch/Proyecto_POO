package org.project.salesystem.admin.dao.implementation;

import org.junit.jupiter.api.Test;
import org.project.salesystem.admin.model.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDAOImplTest {

    @Test
    void testCreateSupplier() {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        Supplier supplier = new Supplier(2, "GameWorld Distribution", "5551234567");

        supplierDAO.create(supplier);
        Supplier retrievedSupplier = supplierDAO.read(2);

        assertNotNull(retrievedSupplier);
        assertEquals("GameWorld Distribution", retrievedSupplier.getName());
        assertEquals("5551234567", retrievedSupplier.getPhone());
    }

    @Test
    void testUpdateSupplier() {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        Supplier supplier = supplierDAO.read(1);

        supplier.setName("PixelSupply Co.");
        supplierDAO.update(supplier);

        Supplier updatedSupplier = supplierDAO.read(1);
        assertEquals("PixelSupply Co.", updatedSupplier.getName());
    }

    @Test
    void testDeleteSupplier() {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        supplierDAO.delete(1);

        Supplier deletedSupplier = supplierDAO.read(1);

        assertNull(deletedSupplier);
    }
}