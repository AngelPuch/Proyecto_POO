package org.project.salesystem.customer.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {

    @Test
    public void testSaleSettersAndGetters() {
        Customer customer = new Customer();
        Sale sale = new Sale();

        sale.setSaleId(1);
        sale.setDateOfSale(new Date());
        sale.setTotal(100.50);
        sale.setCustomer(customer);

        assertEquals(1, sale.getSaleId()); // Verifica que el saleId se asignó correctamente
        assertNotNull(sale.getDateOfSale()); // Verifica que la fecha no sea nula
        assertEquals(100.50, sale.getTotal()); // Verifica que el total se asignó correctamente
        assertEquals(customer, sale.getCustomer()); // Verifica que el customer se asignó correctamente
    }

    @Test
    public void testSaleConstructorWithAllFields() {
        Customer customer = new Customer();
        Date saleDate = new Date();
        double totalAmount = 150.75;

        Sale sale = new Sale(1, saleDate, totalAmount, customer);

        assertEquals(1, sale.getSaleId()); // Verifica que el saleId se asignó correctamente
        assertEquals(saleDate, sale.getDateOfSale()); // Verifica que la fecha de la venta es correcta
        assertEquals(totalAmount, sale.getTotal()); // Verifica que el total se asignó correctamente
        assertEquals(customer, sale.getCustomer()); // Verifica que el customer se asignó correctamente
    }

    @Test
    public void testSaleConstructorWithSaleId() {
        int saleId = 5;

        Sale sale = new Sale(saleId);

        assertEquals(saleId, sale.getSaleId()); // Verifica que el saleId se asignó correctamente
    }

    @Test
    public void testSaleDefaultConstructor() {
        Sale sale = new Sale();

        assertNotNull(sale); // Verifica que el objeto no es nulo
        assertEquals(0, sale.getSaleId()); // Verifica que el saleId es 0 por defecto
        assertNull(sale.getDateOfSale()); // Verifica que la fecha es nula por defecto
        assertEquals(0.0, sale.getTotal()); // Verifica que el total es 0 por defecto
        assertNull(sale.getCustomer()); // Verifica que el customer es nulo por defecto
    }

    @Test
    public void testSaleTotalCalculation() {
        Customer customer = new Customer();
        Date saleDate = new Date();
        Sale sale = new Sale(1, saleDate, 100.0, customer);

        sale.setTotal(200.0); // Se actualiza el total

        assertEquals(200.0, sale.getTotal()); // Verifica que el total se actualizó correctamente
    }
}

