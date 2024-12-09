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

    public int getCartItemId() {
        return cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public Product getProduct() {
        return product;
    }


    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
