package org.project.salesystem.customer.dao.implementation;


import org.project.salesystem.customer.model.Customer;
import org.project.salesystem.customer.model.Sale;
import org.project.salesystem.database.DatabaseConnection;
import org.project.salesystem.database.dao.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the DAO interface for managing sales in the database.
 * Provides CRUD operations for the Sale entity.
 */
public class SaleDAOImpl implements DAO<Sale> {

    /**
     * Inserts a new Sale record into the database.
     *
     * @param sale the Sale object containing the data to be inserted.
     * @throws RuntimeException if a SQL exception occurs during the process.
     */
    @Override
    public void create(Sale sale) {
        String query = "INSERT INTO sale (date, customer_id, total) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, convertUtilDateTOsqlDate(sale));
            ps.setInt(2, sale.getCustomer().getCustomerId());
            ps.setDouble(3, sale.getTotal());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    sale.setSaleId(rs.getInt(1)); // Sets the generated ID
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar la nueva venta", e);
        }
    }

    /**
     * Reads a Sale record by its ID.
     *
     * @param id the ID of the sale to be read.
     * @return a Sale object or null if not found.
     * @throws RuntimeException if a SQL exception occurs during the process.
     */
    @Override
    public Sale read(Integer id) {
        Sale sale = null;
        String query = "SELECT sale_id, date, total, name, phone_number, postal_code, street, city, state " +
                "FROM sale INNER JOIN customer ON sale.customer_id = customer.customer_id " +
                "WHERE sale_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sale = convertToSale(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error ala leer la venta con el ID: " + id, e);
        }
        return sale;
    }

    /**
     * Updates an existing Sale record in the database.
     *
     * @param sale the Sale object containing updated data.
     * @throws RuntimeException if a SQL exception occurs during the process.
     */
    @Override
    public void update(Sale sale) {
        String query = "UPDATE sale SET date = ?, total = ? WHERE sale_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDate(1, convertUtilDateTOsqlDate(sale));
            ps.setDouble(2, sale.getTotal());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el registro de la venta", e);
        }
    }

    /**
     * Deletes a Sale record by its ID.
     *
     * @param id the ID of the sale to be deleted.
     * @throws RuntimeException if a SQL exception occurs during the process.
     */
    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM sale WHERE sale_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la venta con el ID: " + id, e);
        }
    }

    /**
     * Reads all Sale records from the database.
     *
     * @return a List of Sale objects.
     * @throws RuntimeException if a SQL exception occurs during the process.
     */
    @Override
    public List<Sale> readAll() {
        List<Sale> saleList = new ArrayList<>();
        String query = "SELECT sale_id, date, total, name, phone_number, postal_code, street, city, state " +
                "FROM sale INNER JOIN customer ON sale.customer_id = customer.customer_id ";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                saleList.add(convertToSale(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los registros de venta", e);
        }
        return saleList;
    }

    /**
     * Converts a java.util.Date to java.sql.Date.
     *
     * @param sale the Sale object containing the date to convert.
     * @return a java.sql.Date object.
     */
    private java.sql.Date convertUtilDateTOsqlDate(Sale sale) {
        java.util.Date utilDate = sale.getDateOfSale();
        return new java.sql.Date(utilDate.getTime());
    }


    private Sale convertToSale(ResultSet rs) throws SQLException {
        Sale sale = new Sale();
        sale.setSaleId(rs.getInt("sale_id"));
        sale.setDateOfSale(rs.getDate("date"));
        sale.setTotal(rs.getDouble("total"));

        Customer customer = new Customer();
        customer.setName(rs.getString("name"));
        customer.setPhoneNumber(rs.getString("phone_number"));
        customer.setPostal_code(rs.getString("postal_code"));
        customer.setStreet(rs.getString("street"));
        customer.setCity(rs.getString("city"));
        customer.setState(rs.getString("state"));
        sale.setCustomer(customer);

        return sale;
    }

}
