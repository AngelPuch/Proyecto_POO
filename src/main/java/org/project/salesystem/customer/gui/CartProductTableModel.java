package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.dao.implementation.CartItemDAOImpl;
import org.project.salesystem.admin.model.Product;
import org.project.salesystem.customer.model.CartItem;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CartProductTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nombre", "Precio", "Cantidad", "Subtotal"};
    private List<CartItem> cartItems;  // Lista de CartItem en lugar de Product

    public CartProductTableModel(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public int getRowCount() {
        return cartItems.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CartItem cartItem = cartItems.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cartItem.getProduct().getName();  // Nombre del producto
            case 1:
                return cartItem.getProduct().getPrice();  // Precio del producto
            case 2:
                return cartItem.getQuantity();  // Cantidad de este producto en el carrito
            case 3:
                return cartItem.getProduct().getPrice() * cartItem.getQuantity();  // Subtotal = precio * cantidad
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;  // La tabla no es editable
    }

    public void updateCartItems(List<CartItem> updatedCartItems) {
        this.cartItems = updatedCartItems;
        fireTableDataChanged();  // Notifica que la tabla necesita ser actualizada
    }

    // Metodo para obtener un CartItem a partir de un índice
    public CartItem getCartItemAt(int rowIndex) {
        return cartItems.get(rowIndex);
    }

    // Metodo para actualizar un CartItem en la lista y refrescar la vista
    public void updateCartItemAt(int rowIndex, CartItem updatedCartItem) {
        cartItems.set(rowIndex, updatedCartItem);
        fireTableRowsUpdated(rowIndex, rowIndex);  // Notificar que la fila ha sido actualizada
    }
}