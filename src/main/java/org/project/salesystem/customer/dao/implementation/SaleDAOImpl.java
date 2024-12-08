package org.project.salesystem.customer.dao.implementation;

import org.project.salesystem.admin.model.Product;
import org.project.salesystem.customer.dao.SaleDAO;
import org.project.salesystem.customer.model.Customer;
import org.project.salesystem.customer.model.Sale;
import org.project.salesystem.customer.model.SaleDetail;
import org.project.salesystem.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the SaleDAO interface for managing sales in the database.
 * Provides CRUD operations for the Sale entity.
 */
public class SaleDAOImpl implements SaleDAO {

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
            throw new RuntimeException("Error while inserting the new sale", e);
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
                    sale = new Sale();
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
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading sale with ID: " + id, e);
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
            throw new RuntimeException("Error updating the sale record", e);
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
            throw new RuntimeException("Error deleting sale with ID: " + id, e);
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
        String query = "SELECT sale_id, name, date, total FROM sale INNER JOIN customer " +
                "WHERE sale.customer_id = customer.customer_id";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Sale sale = new Sale();
                Customer customer = new Customer();
                sale.setSaleId(rs.getInt("sale_id"));
                sale.setDateOfSale(rs.getDate("date"));
                sale.setTotal(rs.getDouble("total"));
                customer.setName(rs.getString("name"));
                sale.setCustomer(customer);
                saleList.add(sale);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all sales records", e);
        }
        return saleList;
    }

    /**
     * Retrieves a list of sales made by a specific customer, including their details.
     *
     * @param customerId the ID of the customer whose sales are to be retrieved.
     * @return a List of Sale objects with SaleDetail data included.
     * @throws RuntimeException if a SQL exception occurs during the process.
     */
    @Override
    public List<Sale> getSalesByCustomerId(int customerId) {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT s.sale_id, s.date, s.total, sd.product_id, p.name AS product_name, sd.quantity, sd.product_total " +
                "FROM sale s " +
                "JOIN saledetail sd ON s.sale_id = sd.sale_id " +
                "JOIN product p ON sd.product_id = p.product_id " +
                "WHERE s.customer_id = ? " +
                "ORDER BY s.date DESC";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            Map<Integer, Sale> saleMap = new HashMap<>();
            while (rs.next()) {
                int saleId = rs.getInt("sale_id");
                Sale sale = saleMap.getOrDefault(saleId, new Sale());

                if (!saleMap.containsKey(saleId)) {
                    sale.setSaleId(saleId);
                    sale.setDateOfSale(rs.getDate("date"));
                    sale.setTotal(rs.getDouble("total"));
                    sale.setDetails(new ArrayList<>());
                    saleMap.put(saleId, sale);
                    sales.add(sale);
                }

                // Create SaleDetail
                SaleDetail saleDetail = new SaleDetail();
                saleDetail.setProduct(new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name")
                ));
                saleDetail.setQuantity(rs.getInt("quantity"));
                saleDetail.setProductTotal(rs.getDouble("product_total"));

                sale.getDetails().add(saleDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving customer sales", e);
        }

        return sales;
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

}
