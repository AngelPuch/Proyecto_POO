package org.project.salesystem.customer.dao;

public interface CartDAO<Cart> {
    void create(Cart c);
    Cart getCartByCustomerId(Integer id);
    void deleteCart(Cart c);
}
