package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.implementation.ProductDAOImpl;
import org.project.salesystem.admin.gui.InventoryPanel;
import org.project.salesystem.admin.model.Category;
import org.project.salesystem.admin.model.Product;
import org.project.salesystem.admin.model.Supplier;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryPanelController {
    private InventoryPanel inventarioPanel;
    private ProductTableModel productTableModel;
    private JTable table;
    private ProductDAOImpl productDAO;

    public InventoryPanelController(InventoryPanel inventarioPanel, ProductTableModel productTableModel, JTable table) {
        this.inventarioPanel = inventarioPanel;
        this.productTableModel = productTableModel;
        this.table = table;
        productDAO = new ProductDAOImpl();
    }

    public void actionAddProduct(){
        if (validateNonEmptyField()){
            Product product = createProduct();
            productTableModel.addProduct(product);
            clearFields();
        }else{
            JOptionPane.showMessageDialog(inventarioPanel, "Todos los campos son obligatorios");
        }
    }

    public void actionDeleteProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1){
            productTableModel.removeProducto(selectedRow);
        }else{
            JOptionPane.showMessageDialog(inventarioPanel, "Selecciona una fila para eliminar");
        }
    }

    public void actionFilterProductList(){
        String searchText = inventarioPanel.getSearchField().getText();
        List<Product> productListFilter = new ArrayList<>();
        for (Product product: productDAO.readAll()){
            if (product.getName().contains(searchText)){
                productListFilter.add(product);
            }
        }
        productTableModel.showFilteredList(productListFilter);
    }

    private boolean validateNonEmptyField(){
        return !inventarioPanel.getNameField().getText().isEmpty() &&
                !inventarioPanel.getPriceField().getText().isEmpty() &&
                !inventarioPanel.getStockField().getText().isEmpty();
    }

    private Product createProduct(){
        Product product = new Product();
        product.setName(inventarioPanel.getNameField().getText());
        product.setPrice(Double.parseDouble(inventarioPanel.getPriceField().getText()));
        product.setStock(Integer.parseInt(inventarioPanel.getStockField().getText()));
        product.setSupplier((Supplier) inventarioPanel.getComboTypeSupplier().getSelectedItem());
        product.setCategory((Category) inventarioPanel.getComboTypeCategory().getSelectedItem());
        return product;
    }

    private void clearFields() {
        inventarioPanel.getNameField().setText("");
        inventarioPanel.getPriceField().setText("");
        inventarioPanel.getStockField().setText("");
    }

}
