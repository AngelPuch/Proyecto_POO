package org.project.salesystem.customer.dao.implementation;

import org.project.salesystem.customer.dao.CartDAO;
import org.project.salesystem.customer.model.Cart;
import org.project.salesystem.customer.model.Customer;
import org.project.salesystem.database.dao.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public Cart getCartByCustomerId(Customer customer) {
        Cart cart = null;
        String query = "SELECT * FROM cart WHERE customer_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, customer.getCustomerId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cart = new Cart(rs.getInt("cart_id"), customer);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el carrito", e);
        }
        return cart;
    }

    @Override
    public void deleteCart(Cart cart) {

    }

}
