package org.project.salesystem.admin.dao.implementation;

import org.project.salesystem.admin.dao.AdminDAO;
import org.project.salesystem.admin.model.Admin;
import org.project.salesystem.database.dao.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public void create(Admin admin) {
        String query = "INSERT INTO admin VALUES (null, ?, ?)";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el nuevo administrador", e);
        }
    }

    @Override
    public Admin read(Integer id) {
        Admin admin = null;
        String query = "SELECT * FROM admin WHERE admin_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    admin = new Admin();
                    admin.setId(rs.getInt("admin_id"));
                    admin.setUsername(rs.getString("username"));
                    admin.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el administrador con le ID: " + id, e);
        }
        return admin;
    }

    @Override
    public void update(Admin admin) {
        String query = "UPDATE admin SET username = ?, password = ? WHERE admin_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.setInt(3, admin.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el registro", e);
        }

    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM admin WHERE admin_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el administrador con ID: " + id, e);
        }
    }

    @Override
    public List<Admin> readAll() {
        List<Admin> adminList = new ArrayList<>();
        String query = "SELECT * FROM admin";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                adminList.add(new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el registro de todos los administradores", e);
        }
        return adminList;
    }

    @Override
    public Admin findAdminByUsernameAndPassword(String username, String password) {
        Admin admin = null;
        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    admin = new Admin();
                    admin.setId(rs.getInt("admin_id"));
                    admin.setUsername(rs.getString("username"));
                    admin.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo autenticar el usuario", e);
        }
        return admin;
    }
}
