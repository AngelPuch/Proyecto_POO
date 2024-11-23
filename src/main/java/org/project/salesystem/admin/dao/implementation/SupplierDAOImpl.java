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

public class SupplierDAOImpl implements DAO<Supplier> {
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
