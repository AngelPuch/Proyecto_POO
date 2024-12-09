package org.project.salesystem.admin.dao.implementation;

import org.project.salesystem.admin.dao.AdminDAO;
import org.project.salesystem.admin.model.Admin;
import org.project.salesystem.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the {@link AdminDAO} interface to provide CRUD operations for the {@link Admin} entity
 * This class handles the interaction with the database, allowing the creation, reading updating,
 * and deletion of administrator records. It also includes a method for authenticating and admin
 * based on username and password
 */

public class AdminDAOImpl implements AdminDAO {

    /**
     * Creates a new admin record in the database
     * @param admin the {@link Admin} object containing the information to be saved
     */
    @Override
    public void create(Admin admin) {
        String query = "INSERT INTO admin (username, password) VALUES (?, ?)";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el nuevo administrador", e);
        }
    }

    /**
     * Reads the admin record from de database with the given ID
     * @param id the ID of the admin to be read
     * @return an {@link Admin} object containing the admin's details, or {@code null} if not found
     */
    @Override
    public Admin read(Integer id) {
        Admin admin = null;
        String query = "SELECT admin_id, username, password FROM admin WHERE admin_id = ?";

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

    /**
     * Updates an existing admin record in the database
     * @param admin the {@link Admin} object containing the updated details
     */
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

    /**
     * Deletes an admin record from the database
     * @param id the ID of the admin to be deleted
     */
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

    /**
     * Retrieves all admin records from the database
     * @return a {@link List} of all {@link Admin} objects
     */
    @Override
    public List<Admin> readAll() {
        List<Admin> adminList = new ArrayList<>();
        String query = "SELECT admin_id, username, password FROM admin";

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

    /**
     * Finds an admin by username and password fot authentication
     * @param username the username of the admin
     * @param password the password of the admin
     * @return an {@link Admin} object if credentials match, otherwise null
     */
    @Override
    public Admin findAdminByUsernameAndPassword(String username, String password) {
        Admin admin = null;
        String query = "SELECT admin_id, username, password FROM admin WHERE username = ? AND password = ?";

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
