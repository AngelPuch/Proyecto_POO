package org.project.salesystem.customer.dao;

import org.project.salesystem.customer.model.Customer;

public interface CartDAO<Cart> {
    void create(Cart c);
    Cart getCartByCustomerId(Customer customer);
    void deleteCart(Cart c);
}
