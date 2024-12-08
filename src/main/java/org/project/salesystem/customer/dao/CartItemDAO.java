package org.project.salesystem.customer.dao;

import org.project.salesystem.customer.model.CartItem;

import java.util.List;

/**
 * Interface defining data access operations for CartItem entities.
 */
public interface CartItemDAO {
    /**
     * Adds a new CartItem to the cart.
     *
     * @param c the CartItem to be added.
     */
    void addCartItem(CartItem c);

    /**
     * Retrieves all CartItems associated with a specific cart.
     *
     * @param cartId the unique identifier of the cart.
     * @return a list of CartItems belonging to the cart.
     */
    List<CartItem> getCartItems(int cartId);

    /**
     * Deletes a specific CartItem by its unique identifier.
     *
     * @param cartItemId the unique identifier of the CartItem to be deleted.
     */
    void deleteCartItem(int cartItemId);

    /**
     * Clears all CartItems from a specific cart.
     *
     * @param cartId the unique identifier of the cart to be cleared.
     */
    void clearCart(int cartId);
}

