package org.project.salesystem.customer.dao;

import org.project.salesystem.customer.model.Cart;
import org.project.salesystem.customer.model.Customer;
import org.project.salesystem.database.dao.DAO;

/**
 * Interface defining basic operations related to shopping carts in the system.
 *
 *
 */
public interface CartDAO extends DAO<Cart> {


    /**
     * Retrieves a cart associated with a specific customer.
     *
     * @param customer the customer whose cart is to be retrieved.
     * @return the cart associated with the customer, or {@code null} if the customer has no existing cart.
     */
    Cart getCartByCustomerId(Customer customer);
}

