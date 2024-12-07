package org.project.salesystem.admin.controller;

import org.project.salesystem.customer.dao.implementation.SaleDAOImpl;
import org.project.salesystem.customer.model.Sale;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Table model for displaying sales data in a table
 * It provides column names and data for each row and column
 */

public class SaleTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nombre cliente", "Fecha compra", "Precio Total"};
    private SaleDAOImpl saleDAO;
    private List<Sale> saleList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public SaleTableModel() {
        this.saleDAO = new SaleDAOImpl();
        saleList = saleDAO.readAll();
    }

    @Override
    public int getRowCount() {
        return saleList.size();
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
        Sale sale = saleList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> sale.getCustomer().getName();
            case 1 -> dateFormat.format(sale.getDateOfSale());
            case 2 -> sale.getTotal();
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 0;
    }

    /**
     * Updates the table with a filtered list of sales
     * @param filteredSaleList the list of sales to display
     */
    public void showFilteredList(List<Sale> filteredSaleList){
        saleList = filteredSaleList;
        fireTableDataChanged();
    }
}
