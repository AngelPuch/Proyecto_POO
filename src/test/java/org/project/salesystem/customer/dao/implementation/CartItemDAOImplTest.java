package org.project.salesystem.customer.dao.implementation;

import org.junit.jupiter.api.Test;
import org.project.salesystem.customer.dao.CartItemDAO;
import org.project.salesystem.customer.model.Cart;
import org.project.salesystem.customer.model.CartItem;
import org.project.salesystem.admin.model.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartItemDAOImplTest {
    @Test
    void testCreateCartItemSuccess() {

        CartItemDAO cartItemDAO = new CartItemDAOImpl();
        Cart cart = new Cart(1);
        Product product = new Product();
        product.setId(1);
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(2);

        assertDoesNotThrow(() -> cartItemDAO.create(cartItem));
    }
    @Test
    void testCreateCartItemFailure() {

        CartItemDAO cartItemDAO = new CartItemDAOImpl();
        Cart cart = new Cart(9999);
        Product product = new Product();
        product.setId(1);
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(2);


        assertThrows(RuntimeException.class, () -> cartItemDAO.create(cartItem));
    }

    @Test
    void testDeleteCartItemSuccess() {

        CartItemDAO cartItemDAO = new CartItemDAOImpl();
        int cartItemId = 1;

        assertDoesNotThrow(() -> cartItemDAO.delete(cartItemId));
    }

    @Test
    void testDeleteCartItemFailure() {

        CartItemDAO cartItemDAO = new CartItemDAOImpl();
        int cartItemId = 9999;


        assertDoesNotThrow(() -> cartItemDAO.delete(cartItemId));
    }

    @Test
    void testGetCartItemsSuccess() {

        CartItemDAO cartItemDAO = new CartItemDAOImpl();
        int cartId = 1;


        List<CartItem> cartItems = cartItemDAO.getCartItems(cartId);

        assertNotNull(cartItems);
        assertFalse(cartItems.isEmpty());
    }

    @Test
    void testGetCartItemsEmpty() {

        CartItemDAO cartItemDAO = new CartItemDAOImpl();
        int cartId = 9999;

        List<CartItem> cartItems = cartItemDAO.getCartItems(cartId);

        assertNotNull(cartItems);
        assertTrue(cartItems.isEmpty());
    }

    @Test
    void testClearCartSuccess() {

        CartItemDAO cartItemDAO = new CartItemDAOImpl();
        int cartId = 1;

        assertDoesNotThrow(() -> cartItemDAO.clearCart(cartId));

        List<CartItem> cartItems = cartItemDAO.getCartItems(cartId);
        assertTrue(cartItems.isEmpty());
    }

    @Test
    void testClearCartFailure() {
        CartItemDAO cartItemDAO = new CartItemDAOImpl();
        int cartId = 9999; // Non-existent cart ID

        assertDoesNotThrow(() -> cartItemDAO.clearCart(cartId));
    }
}
