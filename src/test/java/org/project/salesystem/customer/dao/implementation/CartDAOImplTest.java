package org.project.salesystem.customer.dao.implementation;

import org.junit.jupiter.api.Test;
import org.project.salesystem.admin.dao.AdminDAO;
import org.project.salesystem.admin.dao.implementation.AdminDAOImpl;
import org.project.salesystem.admin.model.Admin;
import org.project.salesystem.customer.dao.CartDAO;
import org.project.salesystem.customer.dao.CustomerDAO;
import org.project.salesystem.customer.model.Cart;
import org.project.salesystem.customer.model.Customer;
import org.project.salesystem.customer.session.Session;

import static org.junit.jupiter.api.Assertions.*;

public class CartDAOImplTest {
    @Test
    void testGetCartByCustomerSucces(){
        CustomerDAO customerDAO = new CustomerDAOImpl();
        Customer customer =customerDAO.read(1);
        CartDAO cartDAO = new CartDAOImpl();
        Cart cart = cartDAO.getCartByCustomerId(customer);

        assertNotNull(cart);
        assertEquals(1, cart.getCartId());
        assertEquals(customer, cart.getCustomer());
    }

    @Test
    void testGetAdminByUsernameAndPasswordFailure(){
        CustomerDAO customerDAO = new CustomerDAOImpl();
        Customer customer =customerDAO.read(3);
        CartDAO cartDAO = new CartDAOImpl();
        Cart cart = cartDAO.getCartByCustomerId(customer);

        assertNull(cart);
    }

}
