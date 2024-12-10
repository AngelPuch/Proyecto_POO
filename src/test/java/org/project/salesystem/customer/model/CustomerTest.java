package org.project.salesystem.customer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void testCustomerSettersAndGetters() {
        Customer customer = new Customer();

        customer.setName("John Doe");
        customer.setPhoneNumber("1234567890");
        customer.setUsername("johndoe");
        customer.setPassword("password123");
        customer.setStreet("123 Main St");
        customer.setPostal_code("12345");
        customer.setCity("Springfield");
        customer.setState("IL");

        assertEquals("John Doe", customer.getName());
        assertEquals("1234567890", customer.getPhoneNumber());
        assertEquals("johndoe", customer.getUsername());
        assertEquals("password123", customer.getPassword());
        assertEquals("123 Main St", customer.getStreet());
        assertEquals("12345", customer.getPostal_code());
        assertEquals("Springfield", customer.getCity());
        assertEquals("IL", customer.getState());
    }

    @Test
    public void testCustomerConstructor() {
        int customerId = 1;
        String name = "John Doe";
        String phoneNumber = "1234567890";
        String username = "johndoe";
        String password = "password123";
        String street = "123 Main St";
        String postal_code = "12345";
        String city = "Springfield";
        String state = "IL";

        Customer customer = new Customer(customerId, name, phoneNumber, username, password, street, postal_code, city, state);

        assertEquals(customerId, customer.getCustomerId());
        assertEquals(name, customer.getName());
        assertEquals(phoneNumber, customer.getPhoneNumber());
        assertEquals(username, customer.getUsername());
        assertEquals(password, customer.getPassword());
        assertEquals(street, customer.getStreet());
        assertEquals(postal_code, customer.getPostal_code());
        assertEquals(city, customer.getCity());
        assertEquals(state, customer.getState());
    }

    @Test
    public void testCustomerEmptyConstructor() {
        Customer customer = new Customer();
        assertNotNull(customer); // Verifica que el objeto no sea nulo
    }

    @Test
    public void testSetCustomerId() {
        Customer customer = new Customer();
        int customerId = 1001;

        customer.setCustomerId(customerId);
        assertEquals(customerId, customer.getCustomerId()); // Verifica que el customerId se asigna correctamente
    }

    @Test
    public void testSetCustomerName() {
        Customer customer = new Customer();
        String name = "Alice Smith";

        customer.setName(name);

        assertEquals(name, customer.getName()); // Verifica que el nombre se asigna correctamente
    }

    @Test
    public void testSetPhoneNumber() {
        Customer customer = new Customer();
        String phoneNumber = "9876543210";

        customer.setPhoneNumber(phoneNumber);

        assertEquals(phoneNumber, customer.getPhoneNumber()); // Verifica que el número de teléfono se asigna correctamente
    }

    @Test
    public void testSetAddressDetails() {
        Customer customer = new Customer();
        String street = "456 Oak St";
        String postalCode = "67890";
        String city = "Chicago";
        String state = "IL";

        customer.setStreet(street);
        customer.setPostal_code(postalCode);
        customer.setCity(city);
        customer.setState(state);

        assertEquals(street, customer.getStreet());
        assertEquals(postalCode, customer.getPostal_code());
        assertEquals(city, customer.getCity());
        assertEquals(state, customer.getState());
    }
}

