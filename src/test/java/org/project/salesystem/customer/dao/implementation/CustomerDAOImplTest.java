package org.project.salesystem.customer.dao.implementation;

import org.junit.jupiter.api.Test;
import org.project.salesystem.customer.dao.CustomerDAO;
import org.project.salesystem.customer.model.Customer;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOImplTest {

    @Test
    void testCreateCustomer() {
        CustomerDAO customerDAO = new CustomerDAOImpl();

        Customer customer = new Customer(4, "Angel Puch", "7661134536", "angel", "12345", "91203", "Pregrinos", "Xalapa", "Veracruz");

        customerDAO.create(customer);
        Customer retrievedCustomer = customerDAO.read(4);

        assertNotNull(retrievedCustomer);
        assertEquals("Angel Puch", retrievedCustomer.getName());
        assertEquals("7661134536", retrievedCustomer.getPhoneNumber());
        assertEquals("angel", retrievedCustomer.getUsername());
        assertEquals("12345", retrievedCustomer.getPassword());
    }

    @Test
    void testUpdateCustomer() {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        Customer customer =customerDAO.read(3);

        customer.setName("Jose Luis");
        customer.setPhoneNumber("1234567890");
        customer.setUsername("josel");
        customerDAO.update(customer);

        Customer updatedCustomer = customerDAO.read(1);
        assertEquals("Jose Luis", updatedCustomer.getName());
        assertEquals("1234567890", updatedCustomer.getPhoneNumber());
        assertEquals("josel", updatedCustomer.getUsername());
        assertEquals("12345", updatedCustomer.getPassword());
    }

    @Test
    void testFindCustomerByUsernameAndPassword() {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        Customer customer = customerDAO.findCustomerByUsernameAndPassword("josel", "12345");

        assertNotNull(customer);
        assertEquals("Jose Luis", customer.getName());
        assertEquals("1234567890", customer.getPhoneNumber());
        assertEquals("josel", customer.getUsername());
        assertEquals("12345", customer.getPassword());
    }



}