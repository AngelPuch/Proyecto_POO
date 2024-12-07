package org.project.salesystem.customer.gui;
import org.project.salesystem.customer.model.SaleDetail;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SaleDetailTableModel extends AbstractTableModel {

    private final String[] columns = {"Producto", "Cantidad", "Total del Producto"};
    private final List<SaleDetail> details;

    public SaleDetailTableModel(List<SaleDetail> details) {
        this.details = details;
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
            case 0: return detail.getProduct().getName();
            case 1: return detail.getQuantity();
            case 2: return detail.getProductTotal();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}

