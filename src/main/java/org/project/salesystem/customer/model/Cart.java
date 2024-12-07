package org.project.salesystem.customer.model;

import java.io.Serializable;

public class Cart implements Serializable{
    private int cartId;
    private Customer customer;

    public Cart(){
    }

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public Cart(int cartId) {
        this.cartId = cartId;
    }

    public Cart(int cartId, Customer customer) {
        this.cartId = cartId;
        this.customer = customer;
    }

    public Cart(int cartId, Integer customerId) {
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
