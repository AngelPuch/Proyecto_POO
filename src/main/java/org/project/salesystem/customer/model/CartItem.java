package org.project.salesystem.customer.model;

import org.project.salesystem.admin.model.Product;

import java.io.Serializable;

public class CartItem implements Serializable{
    private int cartItemId;
    private int quantity;
    private Cart cart;
    private Product product;

    public CartItem() {
    }

    public CartItem(int cartItemId, int quantity, Cart cart, Product product) {
        this.cartItemId = cartItemId;
        this.quantity = quantity;
        this.cart = cart;
        this.product = product;
    }

    public CartItem( int quantity, Cart cart, Product product) {
        this.quantity = quantity;
        this.cart = cart;
        this.product = product;
    }

    public CartItem( int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
