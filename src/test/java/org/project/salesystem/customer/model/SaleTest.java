package org.project.salesystem.customer.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {

    @Test
    public void testSaleSettersAndGetters() {
        // Given
        Customer customer = new Customer();
        Sale sale = new Sale();

        // When
        sale.setSaleId(1);
        sale.setDateOfSale(new Date());
        sale.setTotal(100.50);
        sale.setCustomer(customer);

        // Then
        assertEquals(1, sale.getSaleId()); // Verifica que el saleId se asignó correctamente
        assertNotNull(sale.getDateOfSale()); // Verifica que la fecha no sea nula
        assertEquals(100.50, sale.getTotal()); // Verifica que el total se asignó correctamente
        assertEquals(customer, sale.getCustomer()); // Verifica que el customer se asignó correctamente
    }

    @Test
    public void testSaleConstructorWithAllFields() {
        // Given
        Customer customer = new Customer();
        Date saleDate = new Date();
        double totalAmount = 150.75;

        // When
        Sale sale = new Sale(1, saleDate, totalAmount, customer);

        // Then
        assertEquals(1, sale.getSaleId()); // Verifica que el saleId se asignó correctamente
        assertEquals(saleDate, sale.getDateOfSale()); // Verifica que la fecha de la venta es correcta
        assertEquals(totalAmount, sale.getTotal()); // Verifica que el total se asignó correctamente
        assertEquals(customer, sale.getCustomer()); // Verifica que el customer se asignó correctamente
    }

    @Test
    public void testSaleConstructorWithSaleId() {
        // Given
        int saleId = 5;

        // When
        Sale sale = new Sale(saleId);

        // Then
        assertEquals(saleId, sale.getSaleId()); // Verifica que el saleId se asignó correctamente
    }

    @Test
    public void testSaleDefaultConstructor() {
        // Given
        Sale sale = new Sale();

        // Then
        assertNotNull(sale); // Verifica que el objeto no es nulo
        assertEquals(0, sale.getSaleId()); // Verifica que el saleId es 0 por defecto
        assertNull(sale.getDateOfSale()); // Verifica que la fecha es nula por defecto
        assertEquals(0.0, sale.getTotal()); // Verifica que el total es 0 por defecto
        assertNull(sale.getCustomer()); // Verifica que el customer es nulo por defecto
    }

    @Test
    public void testSaleTotalCalculation() {
        // Given
        Customer customer = new Customer();
        Date saleDate = new Date();
        Sale sale = new Sale(1, saleDate, 100.0, customer);

        // When
        sale.setTotal(200.0); // Se actualiza el total

        // Then
        assertEquals(200.0, sale.getTotal()); // Verifica que el total se actualizó correctamente
    }
}

