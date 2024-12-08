package org.project.salesystem.customer.dao.implementation;

import org.project.salesystem.admin.model.Product;
import org.project.salesystem.customer.model.Sale;
import org.project.salesystem.customer.model.SaleDetail;
import org.project.salesystem.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods for managing SaleDetail records in the database.
 */
public class SaleDetailDAOImpl {

    /**
     * Inserts a new SaleDetail record into the database.
     *
     * @param saleDetail the SaleDetail object to be inserted.
     * @throws RuntimeException if a SQL exception occurs during the process.
     */
    public void create(SaleDetail saleDetail) {
        String query = "INSERT INTO saledetail (sale_id, product_id, quantity, product_total) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, saleDetail.getSale().getSaleId());
            ps.setInt(2, saleDetail.getProduct().getId());
            ps.setInt(3, saleDetail.getQuantity());
            ps.setDouble(4, saleDetail.getProductTotal());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while inserting the sale detail", e);
        }
    }

    /**
     * Retrieves a list of SaleDetail records associated with a specific Sale ID.
     *
     * @param saleId the ID of the Sale whose details are to be retrieved.
     * @return a List of SaleDetail objects.
     * @throws RuntimeException if a SQL exception occurs during the process.
     */
    public List<SaleDetail> getSaleDetailsBySaleId(int saleId) {
        List<SaleDetail> saleDetails = new ArrayList<>();
        String query = "SELECT sd.sale_detail_id, sd.sale_id, sd.product_id, p.name AS product_name, " +
                "sd.quantity, sd.product_total " +
                "FROM saledetail sd " +
                "JOIN product p ON sd.product_id = p.product_id " +
                "WHERE sd.sale_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, saleId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SaleDetail saleDetail = new SaleDetail();

                    // Populate SaleDetail fields
                    saleDetail.setSale(new Sale(rs.getInt("sale_id")));
                    saleDetail.setSaleDetailId(rs.getInt("sale_detail_id"));
                    saleDetail.setQuantity(rs.getInt("quantity"));
                    saleDetail.setProductTotal(rs.getDouble("product_total"));

                    // Populate Product fields
                    Product product = new Product();
                    product.setId(rs.getInt("product_id"));
                    product.setName(rs.getString("product_name"));
                    saleDetail.setProduct(product);

                    // Add to the list
                    saleDetails.add(saleDetail);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving sale details", e);
        }
        return saleDetails;
    }
}
