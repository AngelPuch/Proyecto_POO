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

public class SaleDetailDAOImpl {

    public void create(SaleDetail saleDetail) {
        String query = "INSERT INTO saledetail (sale_id, product_id, quantity, product_total) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, saleDetail.getSale().getSaleId());
            ps.setInt(2, saleDetail.getProduct().getId());
            ps.setInt(3, saleDetail.getQuantity());
            ps.setDouble(4, saleDetail.getProductTotal());
            ps.executeUpdate();
            System.out.println("Detalle de venta creado para sale_id: " + saleDetail.getSale().getSaleId());
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el detalle de venta", e);
        }
    }

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
                    saleDetail.setSale(new Sale(rs.getInt("sale_id")));
                    saleDetail.setSaleDetailId(rs.getInt("sale_detail_id"));
                    saleDetail.setQuantity(rs.getInt("quantity"));
                    saleDetail.setProductTotal(rs.getDouble("product_total"));

                    // Cargar el producto
                    Product product = new Product();
                    product.setId(rs.getInt("product_id"));
                    product.setName(rs.getString("product_name"));
                    product.setPrice(rs.getDouble("product_price"));
                    saleDetail.setProduct(product);

                    saleDetails.add(saleDetail);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los detalles de la venta", e);
        }
        return saleDetails;
    }

}

