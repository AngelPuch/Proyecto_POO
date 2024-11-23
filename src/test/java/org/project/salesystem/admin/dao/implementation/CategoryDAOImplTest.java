package org.project.salesystem.admin.dao.implementation;

import org.junit.jupiter.api.Test;
import org.project.salesystem.admin.model.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDAOImplTest {

    @Test
    void testCreateCategory() {
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        Category category = new Category(3, "Acción", "Juegos que se centran en combates, desafíos rápidos y reacciones rápidas.");

        categoryDAO.create(category);
        Category retrievedCategory = categoryDAO.read(3);

        assertNotNull(retrievedCategory);
        assertEquals("Acción", retrievedCategory.getName());
        assertEquals("Juegos que se centran en combates, desafíos rápidos y reacciones rápidas.", retrievedCategory.getDescription());
    }

    @Test
    void testUpdateCategory() {
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        Category category = categoryDAO.read(2);

        category.setName("Aventura");
        category.setDescription("Juegos de exploración y resolución de acertijos en mundos inmersivos.");
        categoryDAO.update(category);

        Category updatedCategory = categoryDAO.read(2);
        assertEquals("Aventura", updatedCategory.getName());
        assertEquals("Juegos de exploración y resolución de acertijos en mundos inmersivos.", updatedCategory.getDescription());

    }

    @Test
    void testDeleteCategory() {
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        categoryDAO.delete(2);

        Category deletedCategory = categoryDAO.read(2);

        assertNull(deletedCategory);
    }


}