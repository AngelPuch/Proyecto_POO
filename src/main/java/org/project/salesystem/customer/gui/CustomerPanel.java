package org.project.salesystem.customer.gui;

import javax.swing.*;
import java.awt.*;

public class CustomerPanel extends JPanel {
    private JTable productTable;
    private CustomerProductTableModel tableModel;
    private JButton btnAddToCart;
    private JPanel buttonPanel;
    private JTextField textField;
    private JLabel label;

    public CustomerPanel(){
        setLayout(new BorderLayout());
        init();
        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(productTable), BorderLayout.CENTER);
        setVisible(true);
    }



    private void init(){
        tableModel = new CustomerProductTableModel();
        productTable = new JTable(tableModel);
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        textField = new JTextField(5);
        label = new JLabel("Cantidad de productos: ");

        btnAddToCart = new JButton("Agregar al carrito");
        //btnAddToCart.addActionListener(e -> addToCartAction());
        buttonPanel.add(label);
        buttonPanel.add(textField);
        buttonPanel.add(btnAddToCart);
    }


}
