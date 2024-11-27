package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.implementation.SupplierDAOImpl;
import org.project.salesystem.admin.model.Supplier;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SupplierTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nombre", "Numero de teléfono"};
    private SupplierDAOImpl supplierDAO;
    private List<Supplier> supplierList;

    public SupplierTableModel() {
        this.supplierDAO = new SupplierDAOImpl();
        supplierList = supplierDAO.readAll();
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

    public void addSupplier(Supplier supplier) {
        supplierDAO.create(supplier);
        refreshSupplierList();
        fireTableRowsInserted(supplierList.size() - 1, supplierList.size() - 1);
    }

    public void removeSupplier(int rowIndex) {
        supplierDAO.delete(supplierList.get(rowIndex).getId());
        refreshSupplierList();
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Supplier getSupplierAt(int rowIndex) {
        return supplierList.get(rowIndex);
    }

    public void showFilteredList(List<Supplier> supplierListFilter){
        if (supplierListFilter.isEmpty()){
            refreshSupplierList();
        }else{
            supplierList = supplierListFilter;
            fireTableDataChanged();
        }
    }

    private void refreshSupplierList() {
        supplierList = supplierDAO.readAll();
    }

}
