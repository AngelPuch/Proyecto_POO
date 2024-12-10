package org.project.salesystem.customer.model;

import org.project.salesystem.admin.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartItemTest {

    @Test
    public void testCartItemSettersAndGetters() {
        Cart cart = new Cart();
        Product product = new Product();
        CartItem cartItem = new CartItem();

        cartItem.setCartItemId(1);
        cartItem.setQuantity(3);
        cartItem.setCart(cart);
        cartItem.setProduct(product);

        assertEquals(1, cartItem.getCartItemId()); // Verifica que el cartItemId se asignó correctamente
        assertEquals(3, cartItem.getQuantity()); // Verifica que la cantidad se asignó correctamente
        assertEquals(cart, cartItem.getCart()); // Verifica que el cart se asignó correctamente
        assertEquals(product, cartItem.getProduct()); // Verifica que el producto se asignó correctamente
    }

    @Test
    public void testCartItemConstructor() {
        Cart cart = new Cart();
        Product product = new Product();
        int quantity = 5;

        CartItem cartItem = new CartItem(quantity, cart, product);

        assertEquals(quantity, cartItem.getQuantity()); // Verifica que la cantidad se asignó correctamente
        assertEquals(cart, cartItem.getCart()); // Verifica que el cart se asignó correctamente
        assertEquals(product, cartItem.getProduct()); // Verifica que el producto se asignó correctamente
    }

    @Test
    public void testCartItemEmptyConstructor() {
        CartItem cartItem = new CartItem();

        assertNotNull(cartItem); // Verifica que el objeto no sea nulo
        assertEquals(0, cartItem.getQuantity()); // Verifica que la cantidad inicial sea 0
        assertNull(cartItem.getCart()); // Verifica que el cart esté inicialmente a null
        assertNull(cartItem.getProduct()); // Verifica que el producto esté inicialmente a null
    }

    @Test
    public void testSetAndGetCartItemId() {
        CartItem cartItem = new CartItem();
        int cartItemId = 101;

        cartItem.setCartItemId(cartItemId);

        assertEquals(cartItemId, cartItem.getCartItemId()); // Verifica que el cartItemId se asignó correctamente
    }

    @Test
    public void testSetAndGetQuantity() {
        CartItem cartItem = new CartItem();
        int quantity = 10;

        cartItem.setQuantity(quantity);

        assertEquals(quantity, cartItem.getQuantity()); // Verifica que la cantidad se asignó correctamente
    }

    @Test
    public void testSetAndGetCart() {
        Cart cart = new Cart();
        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);

        assertEquals(cart, cartItem.getCart()); // Verifica que el cart se asignó correctamente
    }

    @Test
    public void testSetAndGetProduct() {
        Product product = new Product();
        CartItem cartItem = new CartItem();

        cartItem.setProduct(product);

        assertEquals(product, cartItem.getProduct()); // Verifica que el producto se asignó correctamente
    }
}

