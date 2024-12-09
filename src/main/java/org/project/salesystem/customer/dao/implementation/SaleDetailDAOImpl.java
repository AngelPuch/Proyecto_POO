package org.project.salesystem.customer.dao.implementation;

import org.project.salesystem.admin.model.Product;
import org.project.salesystem.customer.dao.SaleDetailDAO;
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
 * Provides methods for managing SaleDetail records in the database.
 */
public class SaleDetailDAOImpl implements SaleDetailDAO {

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
            throw new RuntimeException("Error al insertar el nuevo detall venta", e);
        }
    }

    @Override
    public SaleDetail read(Integer id) {
        return null;
    }

    @Override
    public void update(SaleDetail saleDetail) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<SaleDetail> readAll() {
        return List.of();
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
        String query = "SELECT sd.sale_detail_id, sd.quantity, sd.product_total, sd.sale_id, sd.product_id, " +
                "p.name AS product_name, p.price AS product_price " +
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
                    product.setPrice(rs.getDouble("product_price"));
                    saleDetail.setProduct(product);

                    // Add to the list
                    saleDetails.add(saleDetail);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al recuperar los detalle de venta", e);
        }
        return saleDetails;
    }

    @Override
    public List<SaleDetail> getSaleDetailByCustomerId(int customerId) {
        List<SaleDetail> saleDetailList = new ArrayList<>();
        String query = "SELECT s.sale_id, s.date, s.total, sd.product_id, " +
                "p.name AS product_name, sd.quantity, sd.product_total " +
                "FROM saledetail sd " +
                "JOIN sale s ON s.sale_id = sd.sale_id " +
                "JOIN product p ON sd.product_id = p.product_id " +
                "WHERE s.customer_id = ? " +
                "ORDER BY s.date DESC";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, customerId);
            try(ResultSet rs = ps.executeQuery()) {
                Map<Integer, Sale> saleMap = new HashMap<>();
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("product_id"));
                    product.setName(rs.getString("product_name"));

                    Sale sale = new Sale();
                    sale.setSaleId(rs.getInt("sale_id"));
                    sale.setDateOfSale(rs.getDate("date"));
                    sale.setTotal(rs.getDouble("total"));

                    SaleDetail saleDetail = new SaleDetail();
                    saleDetail.setQuantity(rs.getInt("quantity"));
                    saleDetail.setProductTotal(rs.getDouble("product_total"));
                    saleDetail.setProduct(product);
                    saleDetail.setSale(sale);

                    saleDetailList.add(saleDetail);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al recuperar la compra del cliente", e);
        }
        return saleDetailList;
    }

    @Override
    public boolean hasSaleDetailsAssociatedWithProducts(int productId) {
        String query = "SELECT COUNT(*) FROM saledetail WHERE product_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productId);
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
}
