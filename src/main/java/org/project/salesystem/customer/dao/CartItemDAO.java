package org.project.salesystem.customer.dao;

import org.project.salesystem.customer.model.CartItem;
import org.project.salesystem.database.dao.DAO;

import java.util.List;

/**
 * Interface defining data access operations for CartItem entities.
 */
public interface CartItemDAO extends DAO<CartItem> {

    /**
     * Retrieves all CartItems associated with a specific cart.
     *
     * @param cartId the unique identifier of the cart.
     * @return a list of CartItems belonging to the cart.
     */
    List<CartItem> getCartItems(int cartId);


    /**
     * Clears all CartItems from a specific cart.
     *
     * @param cartId the unique identifier of the cart to be cleared.
     */
    void clearCart(int cartId);
}

