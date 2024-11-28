package org.project.salesystem.customer.dao.implementation;

import org.project.salesystem.customer.dao.CartItemDAO;
import org.project.salesystem.customer.model.CartItem;
import org.project.salesystem.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {

    @Override
    public void addCartItem(CartItem cartItem) {
        String query = "INSERT INTO cartitem values (null, ?, ?, ?, ?)";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<CartItem> getCartItems(int cartId) {
        return List.of();
    }

    @Override
    public void updateCartItem(int cartItemId, int quantity) {

    }

    @Override
    public void deleteCartItem(int cartItemId) {

    }

    @Override
    public void clearCart(int cartId) {

    }
}
