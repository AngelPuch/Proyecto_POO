package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.implementation.ProductDAOImpl;
import org.project.salesystem.admin.gui.ProductPanel;
import org.project.salesystem.admin.model.Category;
import org.project.salesystem.admin.model.Product;
import org.project.salesystem.admin.model.Supplier;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProductPanelController {
    private ProductPanel inventarioPanel;
    private ProductTableModel productTableModel;
    private ProductDAOImpl productDAO;

    public ProductPanelController(ProductPanel inventarioPanel, ProductTableModel productTableModel) {
        this.inventarioPanel = inventarioPanel;
        this.productTableModel = productTableModel;
        productDAO = new ProductDAOImpl();
    }

    public void addProductAction(){
        if (validateNonEmptyField()){
            Product product = createProduct();
            productTableModel.addProduct(product);
            clearFields();
        }else{
            JOptionPane.showMessageDialog(inventarioPanel, "Todos los campos son obligatorios");
        }
    }

    public void deleteProductAction() {
        int selectedRow = inventarioPanel.getTable().getSelectedRow();
        if (selectedRow != -1){
            productTableModel.removeProduct(selectedRow);
        }else{
            JOptionPane.showMessageDialog(inventarioPanel, "Selecciona una fila para eliminar");
        }
    }

    public void filterProductListAction(){
        String searchText = inventarioPanel.getSearchField().getText();
        List<Product> filteredProductList = new ArrayList<>();
        for (Product product: productDAO.readAll()){
            if (product.getName().contains(searchText)){
                filteredProductList.add(product);
            }
        }
        productTableModel.showFilteredList(filteredProductList);
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
