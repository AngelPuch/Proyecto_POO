package org.project.salesystem.customer.dao.implementation;

import org.project.salesystem.admin.dao.implementation.ProductDAOImpl;
import org.project.salesystem.admin.model.Product;
import org.project.salesystem.customer.dao.CartItemDAO;
import org.project.salesystem.customer.model.CartItem;
import org.project.salesystem.database.dao.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {

    @Override
    public void addCartItem(CartItem cartItem) {
        String query = "INSERT INTO cart_item (cart_id, product_id, quantity) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cartItem.getCart().getCartId());  // Asociamos al carrito
            ps.setInt(2, cartItem.getProduct().getId());
            ps.setInt(3, cartItem.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar el CartItem", e);
        }
    }

    @Override
    public List<CartItem> getCartItems(int cartId) {
        List<CartItem> cartItems = new ArrayList<>();
        String query = "SELECT * FROM cart_item WHERE cart_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");

                // Obtén el producto usando el productId
                ProductDAOImpl productDAO = new ProductDAOImpl();
                Product product = productDAO.read(productId);

                // Crea el CartItem y añádelo a la lista
                CartItem cartItem = new CartItem(quantity,product);
                cartItems.add(cartItem);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los artículos del carrito", e);
        }

        return cartItems;
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
