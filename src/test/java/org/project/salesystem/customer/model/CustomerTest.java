package org.project.salesystem.customer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void testCustomerSettersAndGetters() {
        // Given
        Customer customer = new Customer();

        // When
        customer.setName("John Doe");
        customer.setPhoneNumber("1234567890");
        customer.setUsername("johndoe");
        customer.setPassword("password123");
        customer.setStreet("123 Main St");
        customer.setPostal_code("12345");
        customer.setCity("Springfield");
        customer.setState("IL");

        // Then
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
        // Given
        int customerId = 1;
        String name = "John Doe";
        String phoneNumber = "1234567890";
        String username = "johndoe";
        String password = "password123";
        String street = "123 Main St";
        String postal_code = "12345";
        String city = "Springfield";
        String state = "IL";

        // When
        Customer customer = new Customer(customerId, name, phoneNumber, username, password, street, postal_code, city, state);

        // Then
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
        // Given
        Customer customer = new Customer();

        // Then
        assertNotNull(customer); // Verifica que el objeto no sea nulo
    }

    @Test
    public void testSetCustomerId() {
        // Given
        Customer customer = new Customer();
        int customerId = 1001;

        // When
        customer.setCustomerId(customerId);

        // Then
        assertEquals(customerId, customer.getCustomerId()); // Verifica que el customerId se asigna correctamente
    }

    @Test
    public void testSetCustomerName() {
        // Given
        Customer customer = new Customer();
        String name = "Alice Smith";

        // When
        customer.setName(name);

        // Then
        assertEquals(name, customer.getName()); // Verifica que el nombre se asigna correctamente
    }

    @Test
    public void testSetPhoneNumber() {
        // Given
        Customer customer = new Customer();
        String phoneNumber = "9876543210";

        // When
        customer.setPhoneNumber(phoneNumber);

        // Then
        assertEquals(phoneNumber, customer.getPhoneNumber()); // Verifica que el número de teléfono se asigna correctamente
    }

    @Test
    public void testSetAddressDetails() {
        // Given
        Customer customer = new Customer();
        String street = "456 Oak St";
        String postalCode = "67890";
        String city = "Chicago";
        String state = "IL";

        // When
        customer.setStreet(street);
        customer.setPostal_code(postalCode);
        customer.setCity(city);
        customer.setState(state);

        // Then
        assertEquals(street, customer.getStreet());
        assertEquals(postalCode, customer.getPostal_code());
        assertEquals(city, customer.getCity());
        assertEquals(state, customer.getState());
    }
}

