package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.gui.InventoryPanel;
import org.project.salesystem.admin.model.Category;
import org.project.salesystem.admin.model.Product;
import org.project.salesystem.admin.model.Supplier;

import javax.swing.*;

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
            Product product = createProduct();
            tableModel.addProduct(product);
            clearFields();
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

    private boolean validateNonEmptyField(){
        return !inventarioPanel.getTxtName().getText().isEmpty() &&
                !inventarioPanel.getTxtPrice().getText().isEmpty() &&
                !inventarioPanel.getTxtStock().getText().isEmpty();
    }

    private Product createProduct(){
        Product product = new Product();
        product.setName(inventarioPanel.getTxtName().getText());
        product.setPrice(Double.parseDouble(inventarioPanel.getTxtPrice().getText()));
        product.setStock(Integer.parseInt(inventarioPanel.getTxtStock().getText()));
        product.setSupplier((Supplier) inventarioPanel.getComboTypeSupplier().getSelectedItem());
        product.setCategory((Category) inventarioPanel.getComboTypeCategory().getSelectedItem());
        return product;
    }

    private void clearFields() {
        inventarioPanel.getTxtName().setText("");
        inventarioPanel.getTxtPrice().setText("");
        inventarioPanel.getTxtStock().setText("");
    }

}
