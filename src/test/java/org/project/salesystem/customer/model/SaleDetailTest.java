package org.project.salesystem.customer.model;

import org.junit.jupiter.api.Test;
import org.project.salesystem.admin.model.Product;

import static org.junit.jupiter.api.Assertions.*;

public class SaleDetailTest {

    @Test
    public void testSaleDetailSettersAndGetters() {
        Sale sale = new Sale();
        Product product = new Product();
        SaleDetail saleDetail = new SaleDetail();

        saleDetail.setSaleDetailId(1);
        saleDetail.setQuantity(3);
        saleDetail.setProductTotal(150.0);
        saleDetail.setSale(sale);
        saleDetail.setProduct(product);

        assertEquals(1, saleDetail.getSaleDetailId()); // Verifica que el saleDetailId se asignó correctamente
        assertEquals(3, saleDetail.getQuantity()); // Verifica que la cantidad se asignó correctamente
        assertEquals(150.0, saleDetail.getProductTotal()); // Verifica que el total del producto se asignó correctamente
        assertEquals(sale, saleDetail.getSale()); // Verifica que la venta se asignó correctamente
        assertEquals(product, saleDetail.getProduct()); // Verifica que el producto se asignó correctamente
    }

    @Test
    public void testSaleDetailConstructorWithAllFields() {
        Sale sale = new Sale();
        Product product = new Product();
        int saleDetailId = 1;
        int quantity = 5;
        double productTotal = 200.0;

        SaleDetail saleDetail = new SaleDetail(saleDetailId, quantity, productTotal, sale, product);

        assertEquals(saleDetailId, saleDetail.getSaleDetailId()); // Verifica que el saleDetailId se asignó correctamente
        assertEquals(quantity, saleDetail.getQuantity()); // Verifica que la cantidad se asignó correctamente
        assertEquals(productTotal, saleDetail.getProductTotal()); // Verifica que el total del producto se asignó correctamente
        assertEquals(sale, saleDetail.getSale()); // Verifica que la venta se asignó correctamente
        assertEquals(product, saleDetail.getProduct()); // Verifica que el producto se asignó correctamente
    }

    @Test
    public void testSaleDetailDefaultConstructor() {
        SaleDetail saleDetail = new SaleDetail();

        assertNotNull(saleDetail); // Verifica que el objeto no es nulo
        assertEquals(0, saleDetail.getSaleDetailId()); // Verifica que el saleDetailId es 0 por defecto
        assertEquals(0, saleDetail.getQuantity()); // Verifica que la cantidad es 0 por defecto
        assertEquals(0.0, saleDetail.getProductTotal()); // Verifica que el total del producto es 0 por defecto
        assertNull(saleDetail.getSale()); // Verifica que la venta es nula por defecto
        assertNull(saleDetail.getProduct()); // Verifica que el producto es nulo por defecto
    }

    @Test
    public void testSaleDetailQuantityUpdate() {
        Sale sale = new Sale();
        Product product = new Product();
        SaleDetail saleDetail = new SaleDetail(1, 2, 100.0, sale, product);

        saleDetail.setQuantity(4); // Actualiza la cantidad

        assertEquals(4, saleDetail.getQuantity()); // Verifica que la cantidad se actualizó correctamente
    }

    @Test
    public void testSaleDetailProductTotalUpdate() {
        Sale sale = new Sale();
        Product product = new Product();
        SaleDetail saleDetail = new SaleDetail(1, 3, 150.0, sale, product);

        saleDetail.setProductTotal(180.0); // Actualiza el total del producto

        assertEquals(180.0, saleDetail.getProductTotal()); // Verifica que el total del producto se actualizó correctamente
    }
}

