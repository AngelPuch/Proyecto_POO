package org.project.salesystem.customer.controller;

import org.project.salesystem.admin.model.Product;
import org.project.salesystem.customer.gui.CustomerPanel;
import org.project.salesystem.customer.gui.CustomerProductTableModel;
import org.project.salesystem.customer.dao.implementation.CartDAOImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CustomerPanelController(){
    private CustomerPanel customerPanel;
    private CustomerProductTableModel customerProductTableModel;
    private JTable table;
    private CartDAOImpl cartDAO;

    public CustomerPanelController(CustomerPanel customerPanel, CustomerProductTableModel customerProductTableModel, JTable table, CartDAOImpl cartDAO) {
        this.customerPanel = customerPanel;
        this.customerProductTableModel = customerProductTableModel;
        this.table = table;
        this.cartDAO = cartDAO;
    }

    public void addProductToCart() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1){
            Product selectedProduct = tableModel.getProductAt(selectedRow);
            JOptionPane.showMessageDialog(this,"Producto añadido al carrito: " + selectedProduct.getName());
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para agregar al carrito");
        }
    }
}
