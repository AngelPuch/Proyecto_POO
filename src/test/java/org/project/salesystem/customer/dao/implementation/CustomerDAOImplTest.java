package org.project.salesystem.customer.dao.implementation;

import org.junit.jupiter.api.Test;
import org.project.salesystem.customer.dao.CustomerDAO;
import org.project.salesystem.customer.model.Address;
import org.project.salesystem.customer.model.Customer;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOImplTest {
/*
    @Test
    void testCreateCustomer() {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        Address address = new Address(1, "91020", "Av. Xalapa", 14155, "Xalapa-Enríquez", "Veracruz", "Mexico");
        Customer customer = new Customer(1, "Angel Puch", "7661134536", "angel", "12345", address);

        customerDAO.create(customer);
        Customer retrievedCustomer = customerDAO.read(1);

        assertNotNull(retrievedCustomer);
        assertEquals("Angel Puch", retrievedCustomer.getName());
        assertEquals("7661134536", retrievedCustomer.getPhoneNumber());
        assertEquals("angel", retrievedCustomer.getUsername());
        assertEquals("12345", retrievedCustomer.getPassword());
        assertEquals("91020", retrievedCustomer.getAddress().getPostalCode());
        assertEquals("Av. Xalapa", retrievedCustomer.getAddress().getStreet());
        assertEquals("Xalapa-Enríquez", retrievedCustomer.getAddress().getCity());
        assertEquals("Veracruz", retrievedCustomer.getAddress().getState());
        assertEquals("Mexico", retrievedCustomer.getAddress().getCountry());
    }

    @Test
    void testUpdateCustomer() {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        Customer customer =customerDAO.read(1);

        customer.setName("Jose Luis");
        customer.setPhoneNumber("1234567890");
        customer.setUsername("josel");
        customerDAO.update(customer);

        Customer updatedCustomer = customerDAO.read(1);
        assertEquals("Jose Luis", updatedCustomer.getName());
        assertEquals("1234567890", updatedCustomer.getPhoneNumber());
        assertEquals("josel", updatedCustomer.getUsername());
        assertEquals("12345", updatedCustomer.getPassword());
        assertEquals("91020", updatedCustomer.getAddress().getPostalCode());
        assertEquals("Av. Xalapa", updatedCustomer.getAddress().getStreet());
        assertEquals("Xalapa-Enríquez", updatedCustomer.getAddress().getCity());
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
        assertEquals("91020", customer.getAddress().getPostalCode());
        assertEquals("Av. Xalapa", customer.getAddress().getStreet());
        assertEquals("Xalapa-Enríquez", customer.getAddress().getCity());
    }

 */

}