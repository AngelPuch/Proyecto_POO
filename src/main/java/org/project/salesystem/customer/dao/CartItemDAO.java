package org.project.salesystem.customer.dao;

import org.project.salesystem.customer.model.CartItem;


import java.util.List;

public interface CartItemDAO {
    void addCartItem(CartItem c); // Añadir producto
    List<CartItem> getCartItems(int cartId); // Leer todos los productos del carrito
    void updateCartItem(int cartItemId, int quantity); // Actualizar cantidad
    void deleteCartItem(int cartItemId); // Eliminar producto específico
    void clearCart(int cartId); // Vaciar el carrito (opcional)
}
