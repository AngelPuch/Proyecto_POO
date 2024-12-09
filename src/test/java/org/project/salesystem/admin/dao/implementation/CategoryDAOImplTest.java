package org.project.salesystem.admin.dao.implementation;

import org.junit.jupiter.api.Test;
import org.project.salesystem.admin.model.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDAOImplTest {

    @Test
    void testCreateCategory() {
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        Category category = new Category(26, "Prueba Crear", "Descripción crear");

        categoryDAO.create(category);
        Category retrievedCategory = categoryDAO.read(26);

        assertNotNull(retrievedCategory);
        assertEquals("Prueba Crear", retrievedCategory.getName());
        assertEquals("Descripción crear", retrievedCategory.getDescription());
    }

    @Test
    void testUpdateCategory() {
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        Category category = categoryDAO.read(23);

        category.setName("Prueba Actualizar");
        category.setDescription("Descripción actualizada");
        categoryDAO.update(category);

        Category updatedCategory = categoryDAO.read(23);
        assertEquals("Prueba Actualizar", updatedCategory.getName());
        assertEquals("Descripción actualizada", updatedCategory.getDescription());

    }

    @Test
    void testDeleteCategory() {
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        categoryDAO.delete(24);

        Category deletedCategory = categoryDAO.read(24);

        assertNull(deletedCategory);
    }


}