package org.project.salesystem.customer.dao.implementation;

import org.project.salesystem.customer.dao.CartDAO;
import org.project.salesystem.customer.model.Cart;
import org.project.salesystem.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CartDAOImpl implements CartDAO<Cart>{

    @Override
    public void create(Cart cart) {
        String query = "insert into cart values (null, ?)";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cart.getCustomer().getCustomerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el carrito", e);
        }
    }

    @Override
    public Cart getCartByCustomerId(Integer id) {
        Cart cart = null;
        String query = "SELECT ";
        return null;
    }

    @Override
    public void deleteCart(Cart cart) {

    }

}
