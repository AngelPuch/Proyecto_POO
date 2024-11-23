package org.project.salesystem.customer.dao.implementation;

import org.project.salesystem.customer.model.Sale;
import org.project.salesystem.database.DatabaseConnection;
import org.project.salesystem.database.dao.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleDAOImpl implements DAO<Sale> {

    @Override
    public void create(Sale sale) {
        String query = "INSERT INTO sale VALUES (null, ?, ?, ?)";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDate(1, convertUtilDateTOsqlDate(sale));
            ps.setInt(2, sale.getCustomer().getCustomerId());
            ps.setDouble(3, sale.getTotal());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar la nueva venta", e);
        }
    }

    @Override
    public Sale read(Integer id) {
        Sale sale = null;
        String query = "select sale_id, date, total from sale WHERE sale_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sale = new Sale();
                    sale.setSaleId(rs.getInt("sale_id"));
                    sale.setDateOfSale(rs.getDate("date"));
                    sale.setTotal(rs.getDouble("total"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar la venta con el ID: " + id, e);
        }
        return null;
    }

    @Override
    public void update(Sale sale) {
        String query = "UPDATE sale SET date = ?, total = ? WHERE sale_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDate(1, convertUtilDateTOsqlDate(sale));
            ps.setDouble(2, sale.getTotal());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el registro", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM sale WHERE sale_id = ?";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la venta con el ID: " + id, e);
        }
    }

    @Override
    public List<Sale> readAll() {
        List<Sale> saleList = new ArrayList<>();
        String query = "select sale_id, date, total from sale";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setSaleId(rs.getInt("sale_id"));
                sale.setDateOfSale(rs.getDate("date"));
                sale.setTotal(rs.getDouble("total"));
                saleList.add(sale);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el registro de todas las ventas", e);
        }
        return saleList;
    }

    //Convierte java.util.Date a un tipo java.sql.Date
    private java.sql.Date convertUtilDateTOsqlDate(Sale sale){
        java.util.Date utilDate = sale.getDateOfSale();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }
}
