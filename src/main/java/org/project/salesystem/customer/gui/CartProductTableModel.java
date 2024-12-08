package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.model.CartItem;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Custom table model to represent the cart items in the shopping cart table.
 * It displays information about each product in the cart such as name, price,
 * quantity, and subtotal.
 */
public class CartProductTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Name", "Price", "Quantity", "Subtotal"};
    private List<CartItem> cartItems;  // List of CartItem objects

    /**
     * Constructor that initializes the table model with the list of cart items.
     *
     * @param cartItems List of cart items to be displayed in the table.
     */
    public CartProductTableModel(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    /**
     * Returns the number of rows (cart items) in the table.
     *
     * @return The number of rows in the table.
     */
    @Override
    public int getRowCount() {
        return cartItems.size();
    }

    /**
     * Returns the number of columns (fixed to 4: Name, Price, Quantity, Subtotal).
     *
     * @return The number of columns in the table.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the name of the column at a given index.
     *
     * @param column The column index.
     * @return The name of the column.
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Returns the value of a cell in the table at a specific row and column.
     *
     * @param rowIndex    The row index of the cell.
     * @param columnIndex The column index of the cell.
     * @return The value at the specified row and column.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CartItem cartItem = cartItems.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cartItem.getProduct().getName();  // Product name
            case 1:
                return cartItem.getProduct().getPrice();  // Product price
            case 2:
                return cartItem.getQuantity();  // Product quantity in the cart
            case 3:
                return cartItem.getProduct().getPrice() * cartItem.getQuantity();  // Subtotal = price * quantity
            default:
                return null;
        }
    }

    /**
     * Returns whether a cell is editable or not. In this case, the table is not editable.
     *
     * @param rowIndex    The row index of the cell.
     * @param columnIndex The column index of the cell.
     * @return false since the table is not editable.
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;  // The table is not editable
    }

    /**
     * Removes a cart item at a specific row index.
     *
     * @param rowIndex The row index of the item to be removed.
     */
    public void removeCartItem(int rowIndex) {
        cartItems.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    /**
     * Returns the list of all cart items.
     *
     * @return The list of cart items.
     */
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    /**
     * Clears all items in the cart.
     */
    public void clearCartItems() {
        cartItems.clear();
        fireTableDataChanged();
    }

    /**
     * Returns a cart item at a specific row index.
     *
     * @param rowIndex The row index of the cart item.
     * @return The CartItem object at the specified index.
     */
    public CartItem getCartItemAt(int rowIndex) {
        return cartItems.get(rowIndex);
    }
}
