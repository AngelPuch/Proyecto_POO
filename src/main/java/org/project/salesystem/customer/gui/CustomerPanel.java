package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.controller.CustomerPanelController;

import javax.swing.*;
import java.awt.*;

public class CustomerPanel extends JPanel {
    private JTable productTable;
    private CustomerProductTableModel tableModel;
    private JButton btnAddToCart;
    private JPanel buttonPanel;
    private JTextField textField;
    private JLabel label;
    private CustomerPanelController controller;

    public CustomerPanel(){
        setLayout(new BorderLayout());
        init();
        controller = new CustomerPanelController(this);
        add(buttonPanel, BorderLayout.SOUTH);
        add(new JScrollPane(productTable), BorderLayout.CENTER);
        setVisible(true);
    }

    public JTable getProductTable() {
        return productTable;
    }

    public JTextField getTextField() {
        return textField;
    }

    public CustomerProductTableModel getTableModel() {
        return tableModel;
    }

    private void init(){
        tableModel = new CustomerProductTableModel();
        productTable = new JTable(tableModel);
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        textField = new JTextField(5);
        label = new JLabel("Cantidad de productos: ");

        btnAddToCart = new JButton("Agregar al carrito");
        btnAddToCart.addActionListener(e -> controller.addToCartAction());
        buttonPanel.add(label);
        buttonPanel.add(textField);
        buttonPanel.add(btnAddToCart);
    }


}
