package org.project.salesystem.customer.controller;

import org.project.salesystem.customer.dao.implementation.SaleDetailDAOImpl;
import org.project.salesystem.customer.model.SaleDetail;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * This class represents a table model for displaying sale details in a JTable.
 * It includes information about the product, quantity, and total price for each sale detail.
 */
public class PurchaseTableModel extends AbstractTableModel {

    private final String[] columns = {"Producto", "Cantidad", "Total", "Fecha de compra"};
    private final List<SaleDetail> details;
    private SaleDetailDAOImpl saleDetailDAO;

    /**
     * Constructor that initializes the sale details.
     *
     * @param customerId List of SaleDetail objects representing the details of the sale.
     */
    public PurchaseTableModel(int customerId) {
        this.saleDetailDAO = new SaleDetailDAOImpl();
        this.details = saleDetailDAO.getSaleDetailByCustomerId(customerId);
    }


    @Override
    public int getRowCount() {
        return details.size();
    }


    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SaleDetail detail = details.get(rowIndex);
        switch (columnIndex) {
            case 0: return detail.getProduct().getName();  // Product name
            case 1: return detail.getQuantity();  // Quantity of the product
            case 2: return detail.getProductTotal();
            case 3: return detail.getSale().getDateOfSale();// Total price of the product
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
