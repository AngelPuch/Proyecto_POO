package org.project.salesystem.admin.dao.implementation;

import org.project.salesystem.admin.dao.ProductDAO;
import org.project.salesystem.admin.model.Category;
import org.project.salesystem.admin.model.Product;
import org.project.salesystem.admin.model.Supplier;
import org.project.salesystem.database.DatabaseConnection;
import org.project.salesystem.database.dao.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link DAO} interface for managing {@link Product} entities
 * Provides CRUD operations for interacting with the database to handle products
 */

public class ProductDAOImpl implements ProductDAO {

    /**
     * Inserts a new product record into the database
     * @param product the {@link Product} object containing the data to be inserted
     */
    @Override
    public void create(Product product) {
        String query = "INSERT INTO product (name, price, stock, supplier_id, category_id) " +
                "VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            setProductValues(product, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el nuevo producto", e);
        }
    }

    /**
     * Retrieves a product record by its ID
     * @param id the unique identifier of the product to retrieve
     * @return The {@link Product} object with the specified ID, or {@code null} if not found
     */
    @Override
    public Product read(Integer id) {
        Product product = null;
        String query = "SELECT p.product_id, p.name, p.price, p.stock, " +
                "p.supplier_id, s.name AS supplier_name, s.phone_number, " +
                "p.category_id, c.name AS category_name, c.description " +
                "FROM product p " +
                "JOIN supplier s ON p.supplier_id = s.supplier_id " +
                "JOIN category c ON p.category_id = c.category_id " +
                "WHERE p.product_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = convertToProduct(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el producto con el ID: " + id, e);
        }
        return product;
    }

    /**
     * Updates an existing product record in the database
     * @param product the {@link Product} object containing the updated data
     */
    @Override
    public void update(Product product) {
        String query = "UPDATE product SET name = ?, price = ?, stock = ?, supplier_id = ?, " +
                "category_id = ? WHERE product_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            setProductValues(product, ps);
            ps.setInt(6, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el registro", e);
        }
    }

    /**
     * Deletes a product record from the database by its ID
     * @param id the unique identifier of the product to delete
     */
    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM product WHERE product_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el producto con el ID: " + id, e);
        }
    }

    /**
     * Retrieves all product records from the database
     * @return a {@link List} of {@link Product} objects representing all the products in the database
     */
    @Override
    public List<Product> readAll() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT p.product_id, p.name, p.price, p.stock, " +
                "p.supplier_id, s.name AS supplier_name, s.phone_number, " +
                "p.category_id, c.name AS category_name, c.description " +
                "FROM product p " +
                "JOIN supplier s ON p.supplier_id = s.supplier_id " +
                "JOIN category c ON p.category_id = c.category_id ";

        try(Connection conn  = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                productList.add(convertToProduct(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el registro de todos los productos", e);
        }
        return productList;
    }

    /**
     * Sets the values of a {@link Product} object in a {@link PreparedStatement}
     * @param product the {@link Product} object containing the data
     * @param ps the {@link PreparedStatement} to populate with the product's data
     * @throws SQLException if an error occurs during the process
     */
    private void setProductValues(Product product, PreparedStatement ps) throws SQLException {
        ps.setString(1, product.getName());
        ps.setDouble(2, product.getPrice());
        ps.setInt(3, product.getStock());
        ps.setInt(4, product.getSupplier().getId());
        ps.setInt(5, product.getCategory().getId());
    }

    /**
     * Converts a {@link ResultSet} into a {@link Product} object
     * @param rs the {@link ResultSet} containing the product data
     * @return the corresponding {@link Product} object
     * @throws SQLException if an error occurs during the process
     */
    private Product convertToProduct(ResultSet rs) throws SQLException {
        Category category = new Category(
                rs.getInt("category_id"),
                rs.getString("category_name"),
                rs.getString("description")
        );
        Supplier supplier = new Supplier(
                rs.getInt("supplier_id"),
                rs.getString("supplier_name"),
                rs.getString("phone_number")
        );
        Product product = new Product(
                rs.getInt("product_id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("stock"),
                category,
                supplier
        );

        return product;
    }

    @Override
    public boolean hasProductsAssociatedWithSupplier(int supplierId) {
        String query = "SELECT COUNT(*) FROM product WHERE supplier_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, supplierId);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean hasProductsAssociatedWithCategory(int categoryID) {
        String query = "SELECT COUNT(*) FROM product WHERE category_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, categoryID);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void updateProductStock(int productId, int quantitySold) {
        String query = "UPDATE product SET stock = stock - ? WHERE product_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, quantitySold);
            ps.setInt(2, productId);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el stock", e);
        }
    }
}
