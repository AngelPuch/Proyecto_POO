package org.project.salesystem.customer.dao.implementation;

import org.project.salesystem.customer.dao.CartDAO;
import org.project.salesystem.customer.model.Cart;
import org.project.salesystem.customer.model.Customer;
import org.project.salesystem.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of the CartDAO interface to manage Cart objects in the database.
 * Provides methods for creating a cart, retrieving a cart by customer ID,
 * and ensuring a cart exists for a given customer.
 */
public class CartDAOImpl implements CartDAO<Cart> {

    /**
     * Creates a new cart in the database for the specified customer.
     *
     * @param cart the Cart object to be created, with the associated customer.
     * @throws RuntimeException if an SQL exception occurs during the process.
     */
    @Override
    public void create(Cart cart) {
        String query = "INSERT INTO cart (customer_id) VALUES (?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, cart.getCustomer().getCustomerId());
            ps.executeUpdate();

            // Retrieve and set the generated cart ID
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cart.setCartId(rs.getInt(1)); // Assigns the generated ID to the Cart object
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating the cart", e);
        }
    }

    /**
     * Retrieves the cart associated with a specific customer ID from the database.
     *
     * @param customer the Customer whose cart is to be retrieved.
     * @return the Cart object if found, or null if no cart exists for the customer.
     * @throws RuntimeException if an SQL exception occurs during the process.
     */
    @Override
    public Cart getCartByCustomerId(Customer customer) {
        Cart cart = null;
        String query = "SELECT * FROM cart WHERE customer_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, customer.getCustomerId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cart = new Cart(rs.getInt("cart_id"), customer); // Create a Cart object with retrieved data
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving the cart", e);
        }
        return cart;
    }

    /**
     * Ensures a cart exists for the given customer. If no cart exists, creates a new one.
     *
     * @param customer the Customer for whom the cart is to be ensured.
     * @return the existing or newly created Cart object.
     * @throws RuntimeException if an SQL exception occurs during the process.
     */
    public Cart getOrCreateCartForCustomer(Customer customer) {
        Cart cart = getCartByCustomerId(customer);  // Attempt to retrieve the existing cart
        if (cart == null) {
            cart = new Cart(customer);  // Create a new cart if none exists
            create(cart);  // Save the cart in the database
        }
        return cart;
    }
}

