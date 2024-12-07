package org.project.salesystem.customer.dao.implementation;

import org.project.salesystem.customer.dao.CartDAO;
import org.project.salesystem.customer.model.Cart;
import org.project.salesystem.customer.model.Customer;
import org.project.salesystem.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDAOImpl implements CartDAO<Cart>{

    @Override
    public void create(Cart cart) {
        String query = "INSERT INTO cart (customer_id) VALUES (?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, cart.getCustomer().getCustomerId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cart.setCartId(rs.getInt(1)); // Asigna el ID generado al carrito
                }
            }
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

    public Cart getOrCreateCartForCustomer(Customer customer) {
        Cart cart = getCartByCustomerId(customer);  // Intenta obtener el carrito existente
        if (cart == null) {
            cart = new Cart(customer);  // Crea un nuevo carrito si no existe
            create(cart);  // Guarda el carrito en la base de datos
            System.out.println("Nuevo carrito creado para el cliente: " + customer.getUsername());
        } else {
            System.out.println("Carrito existente cargado para el cliente: " + customer.getUsername());
        }
        return cart;
    }

}
