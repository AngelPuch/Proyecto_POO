package org.project.salesystem.customer.dao.implementation;

import org.junit.jupiter.api.Test;
import org.project.salesystem.admin.dao.implementation.CategoryDAOImpl;
import org.project.salesystem.admin.dao.implementation.SupplierDAOImpl;
import org.project.salesystem.admin.model.Category;
import org.project.salesystem.admin.model.Product;
import org.project.salesystem.admin.model.Supplier;
import org.project.salesystem.customer.model.Sale;
import org.project.salesystem.customer.model.SaleDetail;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaleDetailDAOImplTest {

    /**
     * Prueba para insertar un nuevo detalle de venta.
     */
    @Test
    void testCreateSaleDetailSuccess() {

        SaleDetailDAOImpl saleDetailDAO = new SaleDetailDAOImpl();
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        Sale sale = new Sale(1);
        Category category = categoryDAO.read(1);
        Supplier supplier = supplierDAO.read(1);
        Product product = new Product(1, "Test Product", 50.00, 50, category, supplier);
        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setSale(sale);
        saleDetail.setProduct(product);
        saleDetail.setQuantity(2);
        saleDetail.setProductTotal(100.00);

        assertDoesNotThrow(() -> saleDetailDAO.create(saleDetail));
    }

    /**
     * Prueba para obtener los detalles de una venta existente.
     */
    @Test
    void testGetSaleDetailsBySaleIdSuccess() {

        SaleDetailDAOImpl saleDetailDAO = new SaleDetailDAOImpl();
        int saleId = 1;

        List<SaleDetail> saleDetails = saleDetailDAO.getSaleDetailsBySaleId(saleId);


        assertNotNull(saleDetails, "La lista de detalles de venta no debería ser nula");
        assertFalse(saleDetails.isEmpty(), "La lista de detalles de venta no debería estar vacía");
        assertEquals(saleId, saleDetails.get(0).getSale().getSaleId(), "El ID de la venta debería coincidir");
    }

    /**
     * Prueba para manejar una venta sin detalles.
     */
    @Test
    void testGetSaleDetailsBySaleIdEmpty() {

        SaleDetailDAOImpl saleDetailDAO = new SaleDetailDAOImpl();
        int saleId = 9999;


        List<SaleDetail> saleDetails = saleDetailDAO.getSaleDetailsBySaleId(saleId);


        assertNotNull(saleDetails, "La lista de detalles de venta no debería ser nula");
        assertTrue(saleDetails.isEmpty(), "La lista de detalles de venta debería estar vacía");
    }

    /**
     * Prueba para obtener los detalles de compra de un cliente.
     */
    @Test
    void testGetSaleDetailByCustomerIdSuccess() {
        SaleDetailDAOImpl saleDetailDAO = new SaleDetailDAOImpl();

        int customerId =1;

        List<SaleDetail> saleDetails = saleDetailDAO.getSaleDetailByCustomerId(customerId);

        assertNotNull(saleDetails, "La lista de detalles de venta no debería ser nula.");
        assertFalse(saleDetails.isEmpty(), "La lista de detalles de venta no debería estar vacía.");
    }


    /**
     * Prueba para manejar un cliente sin detalles de venta.
     */
    @Test
    void testGetSaleDetailByCustomerIdEmpty() {

        SaleDetailDAOImpl saleDetailDAO = new SaleDetailDAOImpl();
        int customerId = 9999;

        List<SaleDetail> saleDetails = saleDetailDAO.getSaleDetailByCustomerId(customerId);

        assertNotNull(saleDetails, "La lista de detalles de venta no debería ser nula");
        assertTrue(saleDetails.isEmpty(), "La lista de detalles de venta debería estar vacía");
    }

    /**
     * Prueba para verificar si un producto tiene detalles de venta asociados.
     */
    @Test
    void testHasSaleDetailsAssociatedWithProductsTrue() {

        SaleDetailDAOImpl saleDetailDAO = new SaleDetailDAOImpl();
        int productId = 1;

        boolean result = saleDetailDAO.hasSaleDetailsAssociatedWithProducts(productId);

        assertTrue(result, "Debería devolver true si el producto tiene detalles asociados");
    }

    /**
     * Prueba para un producto sin detalles de venta asociados.
     */
    @Test
    void testHasSaleDetailsAssociatedWithProductsFalse() {

        SaleDetailDAOImpl saleDetailDAO = new SaleDetailDAOImpl();
        int productId = 9999;

        boolean result = saleDetailDAO.hasSaleDetailsAssociatedWithProducts(productId);

        assertFalse(result, "Debería devolver false si el producto no tiene detalles asociados");
    }
}
