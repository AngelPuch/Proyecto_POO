package org.project.salesystem.customer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    public void testCartSettersAndGetters() {
        // Given
        Customer customer = new Customer();
        Cart cart = new Cart(customer);

        // When
        cart.setCartId(1);

        // Then
        assertEquals(1, cart.getCartId()); // Verifica que el ID del carrito es 1
        assertEquals(customer, cart.getCustomer()); // Verifica que el cliente asociado es el mismo
    }

    @Test
    public void testCartConstructorWithCustomer() {
        // Given
        Customer customer = new Customer();

        // When
        Cart cart = new Cart(customer);

        // Then
        assertEquals(customer, cart.getCustomer()); // Verifica que el cliente se asocia correctamente
        assertNotNull(cart.getCustomer()); // Verifica que el cliente no es nulo
    }

    @Test
    public void testCartConstructorWithCartId() {
        // Given
        int cartId = 10;

        // When
        Cart cart = new Cart(cartId);

        // Then
        assertEquals(cartId, cart.getCartId()); // Verifica que el ID del carrito es el esperado
    }

    @Test
    public void testCartConstructorWithCartIdAndCustomer() {
        // Given
        int cartId = 15;
        Customer customer = new Customer();

        // When
        Cart cart = new Cart(cartId, customer);

        // Then
        assertEquals(cartId, cart.getCartId()); // Verifica que el ID del carrito es el esperado
        assertEquals(customer, cart.getCustomer()); // Verifica que el cliente se asocia correctamente
    }

    @Test
    public void testSetCustomer() {
        // Given
        Cart cart = new Cart();
        Customer customer = new Customer();

        // When
        cart.setCustomer(customer);

        // Then
        assertEquals(customer, cart.getCustomer()); // Verifica que el cliente se asigna correctamente
    }

    @Test
    public void testSetCartId() {
        // Given
        Cart cart = new Cart();
        int cartId = 20;

        // When
        cart.setCartId(cartId);

        // Then
        assertEquals(cartId, cart.getCartId()); // Verifica que el ID del carrito se asigna correctamente
    }
}

