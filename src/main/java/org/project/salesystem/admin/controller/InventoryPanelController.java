package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.implementation.CategoryDAOImpl;
import org.project.salesystem.admin.dao.implementation.SupplierDAOImpl;
import org.project.salesystem.admin.gui.InventoryPanel;
import org.project.salesystem.admin.gui.ProductTableModel;
import org.project.salesystem.admin.model.Category;
import org.project.salesystem.admin.model.Product;
import org.project.salesystem.admin.model.Supplier;

import javax.swing.*;
import java.util.List;

public class InventoryPanelController {
    private InventoryPanel inventarioPanel;
    private ProductTableModel tableModel;
    private JTable table;

    public InventoryPanelController(InventoryPanel inventarioPanel, ProductTableModel tableModel, JTable table) {
        this.inventarioPanel = inventarioPanel;
        this.tableModel = tableModel;
        this.table = table;
    }

    public void actionAddProduct(){
        if (validateNonEmptyField()){
            Product product = new Product();
            product.setName(inventarioPanel.getTxtName());
            product.setPrice(Double.parseDouble(inventarioPanel.getTxtPrice()));
            product.setStock(Integer.parseInt(inventarioPanel.getTxtStock()));
            product.setSupplier((Supplier) inventarioPanel.getComboTypeSupplier().getSelectedItem());
            product.setCategory((Category) inventarioPanel.getComboTypeCategory().getSelectedItem());
            tableModel.addProduct(product);
        }else{
            JOptionPane.showMessageDialog(inventarioPanel, "Todos los campos son obligatorios");
        }
    }
    public void actionDeleteProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1){
            tableModel.removeProducto(selectedRow);
        }else{
            JOptionPane.showMessageDialog(inventarioPanel, "Selecciona una fila para eliminar");
        }
    }

    public boolean validateNonEmptyField(){
        return !inventarioPanel.getTxtName().isEmpty() && !inventarioPanel.getTxtPrice().isEmpty() &&
                !inventarioPanel.getTxtStock().isEmpty();
    }

    public void fillComboBoxSupplier() {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        java.util.List<Supplier> supplierList;
        supplierList = supplierDAO.readAll();
        for (Supplier supplier: supplierList){
            inventarioPanel.getComboTypeSupplier().addItem(supplier);
        }
    }

    public void fillComboBoxCategory() {
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        List<Category> categoryList;
        categoryList = categoryDAO.readAll();
        for (Category category: categoryList){
            inventarioPanel.getComboTypeCategory().addItem(category);
        }
    }
}
