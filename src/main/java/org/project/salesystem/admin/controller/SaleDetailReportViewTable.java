package org.project.salesystem.admin.controller;

import org.project.salesystem.customer.dao.implementation.SaleDetailDAOImpl;
import org.project.salesystem.customer.model.SaleDetail;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class SaleDetailReportViewTable extends AbstractTableModel {
    private final String[] columnNames = {"Producto", "Precio Unitario", "Cantidad", "Subtotal"};
    private SaleDetailDAOImpl saleDetailDAO;
    private List<SaleDetail> saleDetailList;

    public SaleDetailReportViewTable(int saleID) {
        this.saleDetailDAO = new SaleDetailDAOImpl();
        saleDetailList = saleDetailDAO.getSaleDetailsBySaleId(saleID);
    }

    @Override
    public int getRowCount() {
        return saleDetailList.size();
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
        SaleDetail saleDetail = saleDetailList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> saleDetail.getProduct().getName();
            case 1 -> saleDetail.getProduct().getPrice();
            case 2 -> saleDetail.getQuantity();
            case 3 -> saleDetail.getProductTotal();
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 0;
    }



}
