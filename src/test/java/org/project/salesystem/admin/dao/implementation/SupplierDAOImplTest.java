package org.project.salesystem.admin.dao.implementation;

import org.junit.jupiter.api.Test;
import org.project.salesystem.admin.model.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDAOImplTest {

    @Test
    void testCreateSupplier() {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        Supplier supplier = new Supplier(10, "EjemploPruebaCrear", "5578965478");

        supplierDAO.create(supplier);
        Supplier retrievedSupplier = supplierDAO.read(10);

        assertNotNull(retrievedSupplier);
        assertEquals("EjemploPruebaCrear", retrievedSupplier.getName());
        assertEquals("5578965478", retrievedSupplier.getPhone());
    }

    @Test
    void testUpdateSupplier() {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        Supplier supplier = supplierDAO.read(9);

        supplier.setName("EjemploPruebaActualizar");
        supplierDAO.update(supplier);

        Supplier updatedSupplier = supplierDAO.read(9);
        assertEquals("EjemploPruebaActualizar", updatedSupplier.getName());
    }

    @Test
    void testDeleteSupplier() {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        supplierDAO.delete(10);

        Supplier deletedSupplier = supplierDAO.read(10);

        assertNull(deletedSupplier);
    }
}