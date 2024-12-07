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
        System.out.println("Intentando agregar un item al carrito con cart_id: " + cartId);

        String query = "INSERT INTO cart_item (cart_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cartId);  // Asociamos al carrito
            ps.setInt(2, cartItem.getProduct().getId());
            ps.setInt(3, cartItem.getQuantity());
            ps.executeUpdate();
            System.out.println("Producto agregado al carrito correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al agregar el CartItem: " + e.getMessage());
            throw new RuntimeException("Error al agregar el CartItem", e);
        }
    }

    @Override
    public List<CartItem> getCartItems(int cartId) {
        System.out.println("Entrando al método getCartItems con cartId: " + cartId);
        List<CartItem> cartItems = new ArrayList<>();
        String query = "SELECT c.product_id, quantity, name, price, stock  FROM cart_item c INNER JOIN product p WHERE c.product_id = p.product_id AND cart_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cartId);
            System.out.println("Ejecutando consulta para obtener items del carrito.");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    int productId = rs.getInt("product_id");
                    int quantity = rs.getInt("quantity");
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStock(rs.getInt("stock"));
                    CartItem cartItem = new CartItem(quantity, new Cart(cartId), product);
                    cartItems.add(cartItem);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL en getCartItems: " + e.getMessage());
            throw new RuntimeException("Error al obtener los items del carrito", e);
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
