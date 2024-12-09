package org.project.salesystem.admin.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void testDefaultConstructor() {
        Category category = new Category();

        assertEquals(0, category.getId());
        assertNull(category.getName());
        assertNull(category.getDescription());
    }

    @Test
    void testConstructorWithParameters() {

        Category category = new Category(1, "carreras", "juegos carreras");

        assertEquals(1, category.getId());
        assertEquals("carreras", category.getName());
        assertEquals("juegos carreras", category.getDescription());
    }

    @Test
    void testSettersAndGetters() {
        Category category = new Category();

        category.setId(2);
        category.setName("acción");
        category.setDescription("juegos acción");

        // Verificar que los valores se recuperan correctamente con los getters
        assertEquals(2, category.getId());
        assertEquals("acción", category.getName());
        assertEquals("juegos acción", category.getDescription());
    }

}