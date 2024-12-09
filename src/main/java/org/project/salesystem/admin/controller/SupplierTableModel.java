package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.implementation.SupplierDAOImpl;
import org.project.salesystem.admin.model.Supplier;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Represents the table model for displaying and managing supplier data
 */

public class SupplierTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nombre", "Numero de teléfono"};
    private SupplierDAOImpl supplierDAO;
    private List<Supplier> supplierList;

    public SupplierTableModel() {
        this.supplierDAO = new SupplierDAOImpl();
        supplierList = supplierDAO.readAll();
    }

    public List<Supplier> getSupplierList() {
        return supplierList;
    }

    @Override
    public int getRowCount() {
        return supplierList.size();
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
        Supplier supplier = supplierList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> supplier.getName();
            case 1 -> supplier.getPhone();
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Supplier supplier = supplierList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                supplier.setName(aValue.toString());
                break;
            case 1:
                supplier.setPhone(aValue.toString());
                break;
        }
        supplierDAO.update(supplier);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    /**
     * Adds a new supplier to the table and updates the underlying data
     * @param supplier the {@link Supplier} object to be added
     */
    public void addSupplier(Supplier supplier) {
        supplierDAO.create(supplier);
        refreshSupplierList();
        fireTableRowsInserted(supplierList.size() - 1, supplierList.size() - 1);
    }

    /**
     * Removes a supplier from the table and updates the underlying data
     * @param rowIndex the index of the supplier to be removed from the table
     */
    public void removeSupplier(int rowIndex) {
        supplierDAO.delete(supplierList.get(rowIndex).getId());
        refreshSupplierList();
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    /**
     * Updates the table to display a filtered list of suppliers
     * @param filteredSupplierList the list of {@link Supplier} objects to be displayed
     */
    public void showFilteredList(List<Supplier> filteredSupplierList){
        supplierList = filteredSupplierList;
        fireTableDataChanged();
    }

    /**
     * Refreshes the supplier list from the database
     */
    private void refreshSupplierList() {
        supplierList = supplierDAO.readAll();
    }

}
