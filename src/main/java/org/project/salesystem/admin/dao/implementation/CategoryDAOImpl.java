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

public class CategoryDAOImpl implements DAO<Category> {

    @Override
    public void create(Category category) {
        String query = "INSERT INTO category VALUES (null, ?, ? )";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar la nueva categoria", e);
        }
    }

    @Override
    public Category read(Integer id) {
        Category category = null;
        String query = "SELECT * FROM category WHERE category_id = ?";

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
            throw new RuntimeException("Error al consultar la categoria con el id " + id, e);
        }
        return category;
    }

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

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM category WHERE category_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la categoria con ID: " + id, e);
        }
    }

    @Override
    public List<Category> readAll() {
        List<Category> categoryList = new ArrayList<>();
        String query = "SELECT * FROM category";

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
            throw new RuntimeException("Error al consultar el registro de todas las categorias", e);
        }
        return categoryList;
    }
}
