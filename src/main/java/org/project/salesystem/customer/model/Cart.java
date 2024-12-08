package org.project.salesystem.customer.model;

import java.io.Serializable;

/**
 * Represents a shopping cart belonging to a customer.
 * The cart holds a reference to the customer and a unique cart ID.
 */
public class Cart implements Serializable {
    private int cartId;
    private Customer customer;

    /**
     * Default constructor for Cart.
     */
    public Cart() {
    }

    /**
     * Constructor to create a Cart associated with a specific customer.
     *
     * @param customer The customer associated with the cart.
     */
    public Cart(Customer customer) {
        this.customer = customer;
    }

    /**
     * Constructor to create a Cart with a specific cart ID.
     *
     * @param cartId The ID of the cart.
     */
    public Cart(int cartId) {
        this.cartId = cartId;
    }

    /**
     * Constructor to create a Cart with a specific cart ID and customer.
     *
     * @param cartId The ID of the cart.
     * @param customer The customer associated with the cart.
     */
    public Cart(int cartId, Customer customer) {
        this.cartId = cartId;
        this.customer = customer;
    }

    /**
     * Gets the ID of the cart.
     *
     * @return The cart ID.
     */
    public int getCartId() {
        return cartId;
    }

    /**
     * Sets the ID of the cart.
     *
     * @param cartId The cart ID.
     */
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    /**
     * Gets the customer associated with the cart.
     *
     * @return The customer associated with the cart.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer associated with the cart.
     *
     * @param customer The customer to associate with the cart.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

