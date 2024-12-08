package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.model.SaleDetail;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * This class represents a table model for displaying sale details in a JTable.
 * It includes information about the product, quantity, and total price for each sale detail.
 */
public class SaleDetailTableModel extends AbstractTableModel {

    private final String[] columns = {"Product", "Quantity", "Total of Product"};
    private final List<SaleDetail> details;

    /**
     * Constructor that initializes the sale details.
     *
     * @param details List of SaleDetail objects representing the details of the sale.
     */
    public SaleDetailTableModel(List<SaleDetail> details) {
        this.details = details;
    }

    /**
     * Returns the number of rows in the table, which corresponds to the number of sale details.
     *
     * @return The number of rows.
     */
    @Override
    public int getRowCount() {
        return details.size();
    }

    /**
     * Returns the number of columns in the table, which is fixed at 3 (Product, Quantity, Total).
     *
     * @return The number of columns.
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Returns the value at a specific row and column.
     *
     * @param rowIndex The row index.
     * @param columnIndex The column index.
     * @return The value of the cell at the specified row and column.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SaleDetail detail = details.get(rowIndex);
        switch (columnIndex) {
            case 0: return detail.getProduct().getName();  // Product name
            case 1: return detail.getQuantity();  // Quantity of the product
            case 2: return detail.getProductTotal();  // Total price of the product
            default: return null;
        }
    }

    /**
     * Returns the name of the column at the specified index.
     *
     * @param column The column index.
     * @return The name of the column.
     */
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
