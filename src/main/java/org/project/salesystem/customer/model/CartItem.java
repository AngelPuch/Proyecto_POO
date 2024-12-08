package org.project.salesystem.customer.model;

import org.project.salesystem.admin.model.Product;

import java.io.Serializable;

/**
 * Represents an item in a customer's shopping cart.
 * A CartItem is associated with a specific product and a quantity.
 */
public class CartItem implements Serializable {
    private int cartItemId;
    private int quantity;
    private Cart cart;
    private Product product;

    /**
     * Default constructor for CartItem.
     */
    public CartItem() {
    }

    /**
     * Constructor to create a CartItem with specified quantity, cart, and product.
     *
     * @param quantity The quantity of the product in the cart.
     * @param cart The cart to which the item belongs.
     * @param product The product associated with this item.
     */
    public CartItem(int quantity, Cart cart, Product product) {
        this.quantity = quantity;
        this.cart = cart;
        this.product = product;
    }

    /**
     * Gets the unique identifier of the cart item.
     *
     * @return The cart item ID.
     */
    public int getCartItemId() {
        return cartItemId;
    }

    /**
     * Sets the unique identifier of the cart item.
     *
     * @param cartItemId The cart item ID.
     */
    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    /**
     * Gets the quantity of the product in the cart item.
     *
     * @return The quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in the cart item.
     *
     * @param quantity The quantity of the product.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the cart to which this item belongs.
     *
     * @return The cart associated with this item.
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * Sets the cart to which this item belongs.
     *
     * @param cart The cart to associate with this item.
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * Gets the product associated with this cart item.
     *
     * @return The product of the cart item.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product associated with this cart item.
     *
     * @param product The product to associate with this item.
     */
    public void setProduct(Product product) {
        this.product = product;
    }
}
