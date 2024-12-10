package org.project.salesystem.customer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    public void testCartSettersAndGetters() {
        Customer customer = new Customer();
        Cart cart = new Cart(customer);

        cart.setCartId(1);

        assertEquals(1, cart.getCartId()); // Verifica que el ID del carrito es 1
        assertEquals(customer, cart.getCustomer()); // Verifica que el cliente asociado es el mismo
    }

    @Test
    public void testCartConstructorWithCustomer() {
        Customer customer = new Customer();

        Cart cart = new Cart(customer);

        assertEquals(customer, cart.getCustomer()); // Verifica que el cliente se asocia correctamente
        assertNotNull(cart.getCustomer()); // Verifica que el cliente no es nulo
    }

    @Test
    public void testCartConstructorWithCartId() {
        int cartId = 10;

        Cart cart = new Cart(cartId);

        assertEquals(cartId, cart.getCartId()); // Verifica que el ID del carrito es el esperado
    }

    @Test
    public void testCartConstructorWithCartIdAndCustomer() {
        int cartId = 15;
        Customer customer = new Customer();

        Cart cart = new Cart(cartId, customer);

        assertEquals(cartId, cart.getCartId()); // Verifica que el ID del carrito es el esperado
        assertEquals(customer, cart.getCustomer()); // Verifica que el cliente se asocia correctamente
    }

    @Test
    public void testSetCustomer() {
        Cart cart = new Cart();
        Customer customer = new Customer();

        cart.setCustomer(customer);

        assertEquals(customer, cart.getCustomer()); // Verifica que el cliente se asigna correctamente
    }

    @Test
    public void testSetCartId() {
        Cart cart = new Cart();
        int cartId = 20;

        cart.setCartId(cartId);

        assertEquals(cartId, cart.getCartId()); // Verifica que el ID del carrito se asigna correctamente
    }
}

