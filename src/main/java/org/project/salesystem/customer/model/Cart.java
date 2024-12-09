package org.project.salesystem.customer.model;

import java.io.Serializable;

/**
 * Represents a shopping cart belonging to a customer.
 * The cart holds a reference to the customer and a unique cart ID.
 */
public class Cart implements Serializable {
    private int cartId;
    private Customer customer;

    public Cart() {
    }

    public Cart(Customer customer) {
        this.customer = customer;
    }

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

    public int getCartId() {
        return cartId;
    }

    public Customer getCustomer() {
        return customer;
    }


    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

