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
        Product product = new Product();
        product.setId(3);
        product.setName("FIFA 24");
        product.setPrice(59.99);
        product.setStock(120);
        Category category = new Category(3, "Acción", "Juegos que se centran en combates, desafíos rápidos y reacciones rápidas.");
        product.setCategory(category);
        Supplier supplier = new Supplier(2, "GameWorld Distribution", "5551234567");
        product.setSupplier(supplier);

        productDAO.create(product);
        Product retrievedProducto = productDAO.read(3);

        assertNotNull(retrievedProducto);
        assertEquals("FIFA 24", retrievedProducto.getName());
        assertEquals(59.99, retrievedProducto.getPrice());
        assertEquals(120, retrievedProducto.getStock());
        assertEquals("Acción", retrievedProducto.getCategory().getName());
        assertEquals("GameWorld Distribution", retrievedProducto.getSupplier().getName());
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
        Product product = productDAO.read(3);

        product.setName("GTA V");
        product.setCategory(new Category(2, "Aventura", "Juegos de exploración y resolución de acertijos en mundos inmersivos."));
        productDAO.update(product);

        Product updatedProduct = productDAO.read(3);
        assertEquals("GTA V", updatedProduct.getName());
        assertEquals("Aventura", updatedProduct.getCategory().getName());
    }

    @Test
    void testDeleteProduct() {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        productDAO.delete(3);

        Product deletedProduct = productDAO.read(3);
        assertNull(deletedProduct);

    }
}