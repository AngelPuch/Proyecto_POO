package org.project.salesystem.admin.dao.implementation;

import org.junit.jupiter.api.Test;
import org.project.salesystem.admin.model.Category;
import org.project.salesystem.admin.model.Product;
import org.project.salesystem.admin.model.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOImplTest {

    @Test
    void testCreateProduct() {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        Product product = new Product();
        product.setId(9);
        product.setName("Ejemplo Crear");
        product.setPrice(59.99);
        product.setStock(120);

        Category category = categoryDAO.read(1);
        product.setCategory(category);
        Supplier supplier = supplierDAO.read(1);
        product.setSupplier(supplier);

        productDAO.create(product);
        Product retrievedProducto = productDAO.read(9);

        assertNotNull(retrievedProducto);
        assertEquals("Ejemplo Crear", retrievedProducto.getName());
        assertEquals(59.99, retrievedProducto.getPrice());
        assertEquals(120, retrievedProducto.getStock());
        assertEquals("Acción", retrievedProducto.getCategory().getName());
        assertEquals("PixelTech", retrievedProducto.getSupplier().getName());
    }

    @Test
    void testReadProduct() {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        Product product = productDAO.read(3);

        assertNotNull(product);
    }

    @Test
    void testUpdateProduct() {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        Product product = productDAO.read(9);

        product.setName("Prueba Actualizar");
        product.setCategory(categoryDAO.read(5));
        productDAO.update(product);

        Product updatedProduct = productDAO.read(3);
        assertEquals("Prueba Actualizar", updatedProduct.getName());
        assertEquals("Terror", updatedProduct.getCategory().getName());
    }

    @Test
    void testDeleteProduct() {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        productDAO.delete(9);

        Product deletedProduct = productDAO.read(9);
        assertNull(deletedProduct);

    }
}