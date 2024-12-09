package org.project.salesystem.customer.dao.implementation;

import org.junit.jupiter.api.Test;
import org.project.salesystem.customer.model.Customer;
import org.project.salesystem.customer.model.Sale;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaleDAOImplTest {

    /**
     * Prueba que se pueda crear un registro de venta exitosamente.
     */
    @Test
    void testCreateSaleSuccess() {

        SaleDAOImpl saleDAO = new SaleDAOImpl();
        Customer customer = new Customer(1, "Angel Puch", "7661134536", "angel", "12345", "91203", "Pregrinos", "Xalapa", "Veracruz");
        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setDateOfSale(new Date());
        sale.setTotal(100.50);

        assertDoesNotThrow(() -> saleDAO.create(sale));
        assertNotNull(sale.getSaleId(), "El ID de la venta debería ser generado después de la creación");
    }

    /**
     * Prueba que se pueda leer un registro de venta existente por su ID.
     */
    @Test
    void testReadSaleSuccess() {
        SaleDAOImpl saleDAO = new SaleDAOImpl();
        int saleId = 1;

        Sale sale = saleDAO.read(saleId);

        assertNotNull(sale, "La venta no debería ser nula");
        assertEquals(saleId, sale.getSaleId(), "El ID de la venta debería coincidir");
    }

    /**
     * Prueba que se pueda eliminar un registro de venta existente.
     */
    @Test
    void testDeleteSaleSuccess() {

        SaleDAOImpl saleDAO = new SaleDAOImpl();
        int saleId = 21;

        assertDoesNotThrow(() -> saleDAO.delete(saleId));
    }

    /**
     * Prueba que se puedan leer todos los registros de venta.
     */
    @Test
    void testReadAllSalesSuccess() {

        SaleDAOImpl saleDAO = new SaleDAOImpl();

        List<Sale> sales = saleDAO.readAll();

        assertNotNull(sales, "La lista de ventas no debería ser nula");
        assertFalse(sales.isEmpty(), "La lista de ventas no debería estar vacía si hay datos");
    }

}


