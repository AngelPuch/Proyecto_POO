package org.project.salesystem.admin.gui;

import org.project.salesystem.admin.dao.implementation.CategoryDAOImpl;
import org.project.salesystem.admin.dao.implementation.ProductDAOImpl;
import org.project.salesystem.admin.dao.implementation.SupplierDAOImpl;
import org.project.salesystem.admin.model.Category;
import org.project.salesystem.admin.model.Product;
import org.project.salesystem.admin.model.Supplier;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProductTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nombre", "Precio", "Stock", "Proveedor", "Categoría"};
    ProductDAOImpl productDAO;
    List<Product> productList;
    JComboBox<Supplier> comboTypeSupplier;
    JComboBox<Category> comboTypeCategory;

    public ProductTableModel() {
        this.productDAO = new ProductDAOImpl();
        productList = productDAO.readAll();
        comboTypeSupplier = new JComboBox<>();
        comboTypeCategory = new JComboBox<>();
        fillComboBoxSupplier();
        fillComboBoxCategory();
    }


    @Override
    public int getRowCount() {
        return productList.size();
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

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Product product = productList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                product.setName(aValue.toString());
                break;
            case 1:
                product.setPrice(Double.parseDouble(aValue.toString()));
                break;
            case 2:
                product.setStock(Integer.parseInt(aValue.toString()));
                break;
            case 3:
                product.setSupplier((Supplier) aValue);
                break;
            case 4:
                product.setCategory((Category) aValue);
                break;
        }
        productDAO.update(product);

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void addProduct(Product producto) {
        productDAO.create(producto);
        refreshProductList();
        fireTableRowsInserted(productList.size() - 1, productList.size() - 1);
    }

    public void removeProducto(int rowIndex) {
        productDAO.delete(productList.get(rowIndex).getId());
        refreshProductList();
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Product getProductoAt(int rowIndex) {
        return productList.get(rowIndex);
    }

    private void refreshProductList() {
        productList = productDAO.readAll();
    }

    private void fillComboBoxSupplier() {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        java.util.List<Supplier> supplierList;
        supplierList = supplierDAO.readAll();
        for (Supplier supplier: supplierList){
            comboTypeSupplier.addItem(supplier);
        }
    }

    private void fillComboBoxCategory() {
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        List<Category> categoryList;
        categoryList = categoryDAO.readAll();
        for (Category category: categoryList){
            comboTypeCategory.addItem(category);
        }
    }
}
