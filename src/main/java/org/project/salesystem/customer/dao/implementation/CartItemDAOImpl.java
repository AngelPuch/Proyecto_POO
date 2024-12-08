package org.project.salesystem.customer.dao.implementation;

import org.project.salesystem.admin.dao.implementation.ProductDAOImpl;
import org.project.salesystem.admin.model.Product;
import org.project.salesystem.customer.dao.CartItemDAO;
import org.project.salesystem.customer.model.Cart;
import org.project.salesystem.customer.model.CartItem;
import org.project.salesystem.customer.session.Session;
import org.project.salesystem.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {

    @Override
    public void addCartItem(CartItem cartItem) {
        int cartId = cartItem.getCart().getCartId();

        String query = "INSERT INTO cart_item (cart_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cartId);  // Asociamos al carrito
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
        String query = "SELECT c.cart_item_id, c.product_id, c.quantity, p.name, p.price, p.stock " +
                "FROM cart_item c " +
                "INNER JOIN product p ON c.product_id = p.product_id " +
                "WHERE c.cart_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cartId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CartItem cartItem = new CartItem();
                    cartItem.setCartItemId(rs.getInt("cart_item_id")); // Asignamos el ID
                    cartItem.setQuantity(rs.getInt("quantity"));

                    Product product = new Product();
                    product.setId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStock(rs.getInt("stock"));

                    cartItem.setProduct(product);
                    cartItem.setCart(new Cart(cartId));

                    cartItems.add(cartItem);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los items del carrito", e);
        }

        return cartItems;
    }

    @Override
    public void updateCartItem(int cartItemId, int quantity) {
        String query = "UPDATE cart_item SET quantity = ? WHERE cart_item_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, quantity);
            ps.setInt(2, cartItemId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el CartItem", e);
        }
    }

    @Override
    public void deleteCartItem(int cartItemId) {
        String query = "DELETE FROM cart_item WHERE cart_item_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cartItemId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el CartItem", e);
        }
    }

    @Override
    public void clearCart(int cartId) {
        String query = "DELETE FROM cart_item WHERE cart_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al vaciar el carrito", e);
        }
    }

}
