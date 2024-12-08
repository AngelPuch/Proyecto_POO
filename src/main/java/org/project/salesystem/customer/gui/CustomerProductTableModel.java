package org.project.salesystem.customer.gui;

import org.project.salesystem.admin.dao.implementation.ProductDAOImpl;
import org.project.salesystem.admin.model.Product;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * This class represents the table model for displaying products in the customer panel.
 * It provides the data for the product table and the logic for retrieving product details.
 */
public class CustomerProductTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Name", "Price", "Stock", "Supplier", "Category"};
    private List<Product> productList;

    /**
     * Constructor that initializes the product list by fetching all products from the database.
     */
    public CustomerProductTableModel() {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        this.productList = productDAO.readAll();
    }

    /**
     * Returns the number of rows in the product table.
     *
     * @return The number of products in the list.
     */
    @Override
    public int getRowCount() {
        return productList.size();
    }

    /**
     * Returns the number of columns in the product table.
     *
     * @return The number of columns (5: Name, Price, Stock, Supplier, Category).
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the name of the column based on its index.
     *
     * @param column The column index.
     * @return The name of the column.
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Returns the value at a specific row and column in the product table.
     *
     * @param rowIndex    The row index.
     * @param columnIndex The column index.
     * @return The value of the product attribute for that row and column.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = productList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> product.getName();
            case 1 -> product.getPrice();
            case 2 -> product.getStock();
            case 3 -> product.getSupplier().getName();
            case 4 -> product.getCategory().getName();
            default -> null;
        };
    }

    /**
     * Indicates whether a cell is editable. In this case, no cell is editable.
     *
     * @param rowIndex    The row index.
     * @param columnIndex The column index.
     * @return False, since the table is not editable.
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    /**
     * Returns the product at a specific row.
     *
     * @param rowIndex The row index.
     * @return The product at the specified row.
     */
    public Product getProductAt(int rowIndex) {
        return productList.get(rowIndex);
    }
}
