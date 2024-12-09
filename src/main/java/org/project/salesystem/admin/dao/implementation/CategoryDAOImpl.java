package org.project.salesystem.admin.dao.implementation;

import org.project.salesystem.admin.model.Category;
import org.project.salesystem.database.DatabaseConnection;
import org.project.salesystem.database.dao.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link DAO} interface for managing {@link Category} entities.
 * Provides CRUD operations to interact with the database, allowing the creation, retrieval,
 * updating, and deletion of category records.
 */

public class CategoryDAOImpl implements DAO<Category> {

    /**
     * Inserts a new category record into the database
     * @param category the {@link Category} object containing the data to be inserted
     */
    @Override
    public void create(Category category) {
        String query = "INSERT INTO category (name, description) VALUES (?, ? )";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar la nueva categoría", e);
        }
    }

    /**
     * Retrieves a category record by its ID
     * @param id the unique identifier of the category to retrieve
     * @return the {@link Category} object with the specified ID, or {@code null} if not found
     */
    @Override
    public Category read(Integer id) {
        Category category = null;
        String query = "SELECT category_id, name, description FROM category WHERE category_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    category = new Category();
                    category.setId(rs.getInt("category_id"));
                    category.setName(rs.getString("name"));
                    category.setDescription(rs.getString("description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar la categoría con el id " + id, e);
        }
        return category;
    }

    /**
     * Updates an existing category record in the database
     * @param category the {@link Category} object containing the updated data
     */
    @Override
    public void update(Category category) {
        String query = "UPDATE category SET name = ?, description = ? WHERE category_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el registro", e);
        }
    }

    /**
     * Deletes a category record from the database by its ID
     * @param id the unique identifier of the category to delete
     */
    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM category WHERE category_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la categoría con ID: " + id, e);
        }
    }

    /**
     * Retrieves all category records from the database
     * @return a {@link List} of {@link Category} objects representing all the categories in the database
     */
    @Override
    public List<Category> readAll() {
        List<Category> categoryList = new ArrayList<>();
        String query = "SELECT category_id, name, description FROM category";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                categoryList.add(new Category(
                        rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getString("description"))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el registro de todas las categorías", e);
        }
        return categoryList;
    }
}
