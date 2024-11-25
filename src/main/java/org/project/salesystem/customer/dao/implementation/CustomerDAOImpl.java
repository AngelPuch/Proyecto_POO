package org.project.salesystem.customer.dao.implementation;

import org.project.salesystem.customer.dao.CustomerDAO;
import org.project.salesystem.customer.model.Address;
import org.project.salesystem.customer.model.Customer;
import org.project.salesystem.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public Customer findCustomerByUsernameAndPassword(String username, String password) {
        Customer customer = null;
        String query = "SELECT customer.customer_id, name, phone_number, username, password, customer.addres_id, " +
                "postal_code, street, number, city, state, country " +
                "from customer " +
                "INNER JOIN address ON customer.addres_id = address.addres_id WHERE username = ? AND password = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = convertToCustomer(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo autenticar el usuario", e);
        }

        return customer;
    }

    @Override
    public void create(Customer customer) {
        String query = "INSERT INTO customer VALUES (null, ?, ?, ?, ?, ?)";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            setCustomerValues(customer, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el nuevo cliente", e);
        }
    }

    @Override
    public Customer read(Integer id) {
        Customer customer = null;
        String query = "SELECT customer.customer_id, name, phone_number, username, password, customer.addres_id, " +
                "postal_code, street, number, city, state, country " +
                "FROM customer " +
                "INNER JOIN address ON customer.addres_id = address.addres_id WHERE customer_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = convertToCustomer(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar al cliente con el ID: " + id, e);
        }
        return customer;
    }

    @Override
    public void update(Customer customer) {
        String query = "UPDATE customer SET name = ?, phone_number = ?, username = ?, " +
                "password = ?, addres_id = ? WHERE customer_id = ?";

        try(Connection conn  = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            setCustomerValues(customer, ps);
            ps.setInt(6, customer.getCustomerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el registro", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM customer WHERE customer_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el cliente con el ID: " + id, e);
        }
    }

    @Override
    public List<Customer> readAll() {
        List<Customer> customerList = new ArrayList<>();
        String query = "SELECT customer.customer_id, name, phone_number, username, password, customer.addres_id, " +
                "postal_code, street, number, city, state, country " +
                "from customer " +
                "INNER JOIN address ON customer.addres_id = address.addres_id";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                customerList.add(convertToCustomer(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el registro de todos los clientes", e);
        }
        return customerList;
    }

    private void setCustomerValues(Customer customer, PreparedStatement ps) throws SQLException {
        ps.setString(1, customer.getName());
        ps.setString(2, customer.getPhoneNumber());
        ps.setString(3, customer.getUsername());
        ps.setString(4, customer.getPassword());
        ps.setInt(5, customer.getAddress().getAddressId());
    }

    private Customer convertToCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer(
                rs.getInt("customer_id"),
                rs.getString("name"),
                rs.getString("phone_number"),
                rs.getString("username"),
                rs.getString("password"),
                new Address(
                        rs.getInt("addres_id"),
                        rs.getString("postal_code"),
                        rs.getString("street"),
                        rs.getInt("number"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("country")
                )
        );
        return customer;
    }
}
