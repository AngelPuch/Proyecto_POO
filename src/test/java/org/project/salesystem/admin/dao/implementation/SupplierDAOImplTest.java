package org.project.salesystem.admin.dao.implementation;

import org.junit.jupiter.api.Test;
import org.project.salesystem.admin.model.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDAOImplTest {

    @Test
    void testCreateSupplier() {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        Supplier supplier = new Supplier(8, "EjemploPruebaCrear", "5578965478");

        supplierDAO.create(supplier);
        Supplier retrievedSupplier = supplierDAO.read(8);

        assertNotNull(retrievedSupplier);
        assertEquals("EjemploPruebaCrear", retrievedSupplier.getName());
        assertEquals("5578965478", retrievedSupplier.getPhone());
    }

    @Test
    void testUpdateSupplier() {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        Supplier supplier = supplierDAO.read(8);

        supplier.setName("EjemploPruebaActualizar");
        supplierDAO.update(supplier);

        Supplier updatedSupplier = supplierDAO.read(8);
        assertEquals("EjemploPruebaActualizar", updatedSupplier.getName());
    }

    @Test
    void testDeleteSupplier() {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        supplierDAO.delete(8);

        Supplier deletedSupplier = supplierDAO.read(8);

        assertNull(deletedSupplier);
    }
}