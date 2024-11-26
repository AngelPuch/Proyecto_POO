package org.project.salesystem.customer.gui;

import org.project.salesystem.admin.dao.implementation.ProductDAOImpl;
import org.project.salesystem.admin.model.Product;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CustomerProductTableModel extends AbstractTableModel{
    private final String[] columnNames = {"Nombre", "Precio", "Stock", "Proveedor", "Categoría"};
    private List<Product> productList;

    public CustomerProductTableModel(){
        ProductDAOImpl productDAO = new ProductDAOImpl();
        this.productList = productDAO.readAll();
    }
    @Override
    public int getRowCount(){
        return productList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = productList.get(rowIndex);
        return switch (columnIndex){
            case 0 -> product.getName();
            case 1 -> product.getPrice();
            case 2 -> product.getStock();
            case 3 -> product.getSupplier().getName();
            case 4 -> product.getCategory().getName();
            default -> null;
        };

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }

    public Product getProductAt(int rowIndex) {
        return productList.get(rowIndex);
    }
}
