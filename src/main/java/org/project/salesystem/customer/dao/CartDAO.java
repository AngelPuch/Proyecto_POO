package org.project.salesystem.customer.dao;

import org.project.salesystem.customer.model.Customer;

/**
 * Interface defining basic operations related to shopping carts in the system.
 *
 * @param <Cart> Generic type representing the shopping cart.
 */
public interface CartDAO<Cart> {

    /**
     * Creates a new cart in the database.
     *
     * @param c the cart to be created. This object must contain at least
     *          the reference to the associated customer.
     */
    void create(Cart c);

    /**
     * Retrieves a cart associated with a specific customer.
     *
     * @param customer the customer whose cart is to be retrieved.
     * @return the cart associated with the customer, or {@code null} if the customer has no existing cart.
     */
    Cart getCartByCustomerId(Customer customer);
}

