package org.project.salesystem.customer.model;

import org.project.salesystem.admin.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartItemTest {

    @Test
    public void testCartItemSettersAndGetters() {
        // Given
        Cart cart = new Cart(); // Crear un cart vacío
        Product product = new Product(); // Crear un producto vacío
        CartItem cartItem = new CartItem();

        // When
        cartItem.setCartItemId(1);
        cartItem.setQuantity(3);
        cartItem.setCart(cart);
        cartItem.setProduct(product);

        // Then
        assertEquals(1, cartItem.getCartItemId()); // Verifica que el cartItemId se asignó correctamente
        assertEquals(3, cartItem.getQuantity()); // Verifica que la cantidad se asignó correctamente
        assertEquals(cart, cartItem.getCart()); // Verifica que el cart se asignó correctamente
        assertEquals(product, cartItem.getProduct()); // Verifica que el producto se asignó correctamente
    }

    @Test
    public void testCartItemConstructor() {
        // Given
        Cart cart = new Cart(); // Crear un cart vacío
        Product product = new Product(); // Crear un producto vacío
        int quantity = 5;

        // When
        CartItem cartItem = new CartItem(quantity, cart, product);

        // Then
        assertEquals(quantity, cartItem.getQuantity()); // Verifica que la cantidad se asignó correctamente
        assertEquals(cart, cartItem.getCart()); // Verifica que el cart se asignó correctamente
        assertEquals(product, cartItem.getProduct()); // Verifica que el producto se asignó correctamente
    }

    @Test
    public void testCartItemEmptyConstructor() {
        // Given
        CartItem cartItem = new CartItem();

        // Then
        assertNotNull(cartItem); // Verifica que el objeto no sea nulo
        assertEquals(0, cartItem.getQuantity()); // Verifica que la cantidad inicial sea 0
        assertNull(cartItem.getCart()); // Verifica que el cart esté inicialmente a null
        assertNull(cartItem.getProduct()); // Verifica que el producto esté inicialmente a null
    }

    @Test
    public void testSetAndGetCartItemId() {
        // Given
        CartItem cartItem = new CartItem();
        int cartItemId = 101;

        // When
        cartItem.setCartItemId(cartItemId);

        // Then
        assertEquals(cartItemId, cartItem.getCartItemId()); // Verifica que el cartItemId se asignó correctamente
    }

    @Test
    public void testSetAndGetQuantity() {
        // Given
        CartItem cartItem = new CartItem();
        int quantity = 10;

        // When
        cartItem.setQuantity(quantity);

        // Then
        assertEquals(quantity, cartItem.getQuantity()); // Verifica que la cantidad se asignó correctamente
    }

    @Test
    public void testSetAndGetCart() {
        // Given
        Cart cart = new Cart();
        CartItem cartItem = new CartItem();

        // When
        cartItem.setCart(cart);

        // Then
        assertEquals(cart, cartItem.getCart()); // Verifica que el cart se asignó correctamente
    }

    @Test
    public void testSetAndGetProduct() {
        // Given
        Product product = new Product();
        CartItem cartItem = new CartItem();

        // When
        cartItem.setProduct(product);

        // Then
        assertEquals(product, cartItem.getProduct()); // Verifica que el producto se asignó correctamente
    }
}

