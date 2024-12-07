package org.project.salesystem.admin.dao.implementation;

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
 * Implementation of the {@link DAO} interface for managing {@link Supplier} entities
 * Provides CRUD operations for interacting with the database to handle suppliers
 */

public class SupplierDAOImpl implements DAO<Supplier> {

    /**
     * Inserts a new supplier into the database
     * @param supplier the {@link Supplier} to be inserted
     */
    @Override
    public void create(Supplier supplier) {
        String query = "INSERT INTO supplier VALUES (null, ?, ?)";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el nuevo proveedor", e);
        }
    }

    /**
     * Retrieves a supplier from the database by its ID
     * @param id the identifier of the {@link Supplier} to retrieve
     * @return the {@link Supplier} object, or {@code null} if not found
     */
    @Override
    public Supplier read(Integer id) {
        Supplier supplier = null;
        String query = "SELECT * FROM supplier WHERE supplier_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    supplier = new Supplier();
                    supplier.setId(rs.getInt("supplier_id"));
                    supplier.setName(rs.getString("name"));
                    supplier.setPhone(rs.getString("phone_number"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el proveedor con el ID: + " + id, e);
        }
        return supplier;
    }

    /**
     * Updates the details of an existing supplier in the Database
     * @param supplier the {@link Supplier} object containing updated data
     */
    @Override
    public void update(Supplier supplier) {
        String query = "UPDATE supplier SET name = ?, phone_number = ? WHERE supplier_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getPhone());
            ps.setInt(3, supplier.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el registro", e);
        }
    }

    /**
     * Deletes a supplier from the database by its ID
     * @param id the identifier of the {@link Supplier} to delete
     */
    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM supplier WHERE supplier_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el proveedor con el ID: " + id, e);
        }
    }

    /**
     * Retrieves all supplier records from the database
     * @return a {@link List} of {@link Supplier} objects
     */
    @Override
    public List<Supplier> readAll() {
        List<Supplier> supplierList = new ArrayList<>();
        String query = "SELECT * FROM supplier";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                supplierList.add(new Supplier(
                        rs.getInt("supplier_id"),
                        rs.getString("name"),
                        rs.getString("phone_number")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el registro de todos los proveedores", e);
        }
        return supplierList;
    }
}
