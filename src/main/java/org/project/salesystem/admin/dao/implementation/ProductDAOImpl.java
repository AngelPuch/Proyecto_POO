package org.project.salesystem.admin.dao.implementation;

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

public class ProductDAOImpl implements DAO<Product> {
    @Override
    public void create(Product product) {
        String query = "INSERT INTO product VALUES(null, ?, ?, ?, ?, ?)";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            setProductValues(product, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el nuevo producto", e);
        }
    }

    @Override
    public Product read(Integer id) {
        Product product = null;
        String query = "SELECT product.product_id, product.name, product.price, product.stock, " +
                "product.supplier_id, supplier.name AS supplier_name, supplier.phone_number, " +
                "product.category_id, category.name AS category_name, category.description " +
                "FROM product " +
                "JOIN supplier ON product.supplier_id = supplier.supplier_id " +
                "JOIN category ON product.category_id = category.category_id " +
                "WHERE product.product_id = ?";

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

    @Override
    public List<Product> readAll() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT product.product_id, product.name, product.price, product.stock, " +
                "product.supplier_id, supplier.name AS supplier_name, supplier.phone_number, " +
                "product.category_id, category.name AS category_name, category.description " +
                "FROM product " +
                "JOIN supplier ON product.supplier_id = supplier.supplier_id " +
                "JOIN category ON product.category_id = category.category_id";

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

    private void setProductValues(Product product, PreparedStatement ps) throws SQLException {
        ps.setString(1, product.getName());
        ps.setDouble(2, product.getPrice());
        ps.setInt(3, product.getStock());
        ps.setInt(4, product.getSupplier().getId());
        ps.setInt(5, product.getCategory().getId());
    }

    private Product convertToProduct(ResultSet rs) throws SQLException {
        Product product = new Product(
                rs.getInt("product_id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("stock"),
                new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("description")
                ),
                new Supplier(
                        rs.getInt("supplier_id"),
                        rs.getString("supplier_name"),
                        rs.getString("phone_number")
                )
        );
        return product;
    }


}
