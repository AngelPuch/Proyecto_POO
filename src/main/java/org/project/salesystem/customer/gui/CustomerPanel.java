package org.project.salesystem.customer.gui;

import org.project.salesystem.customer.controller.CustomerPanelController;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the customer panel where products are displayed.
 * The customer can select a quantity of a product and add it to the shopping cart.
 */
public class CustomerPanel extends JPanel {

    private JTable productTable;
    private CustomerProductTableModel tableModel;
    private JButton btnAddToCart;
    private JPanel buttonPanel;
    private JTextField textField;
    private JLabel label;
    private CustomerPanelController controller;

    /**
     * Constructor that initializes the customer panel.
     * It sets up the layout and adds components to the panel.
     */
    public CustomerPanel() {
        setLayout(new BorderLayout());
        init();
        controller = new CustomerPanelController(this);
        add(buttonPanel, BorderLayout.SOUTH);
        add(new JScrollPane(productTable), BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Returns the product table.
     *
     * @return The JTable that displays the products.
     */
    public JTable getProductTable() {
        return productTable;
    }

    /**
     * Returns the text field for entering product quantity.
     *
     * @return The JTextField for quantity input.
     */
    public JTextField getTextField() {
        return textField;
    }

    /**
     * Returns the table model of the customer product table.
     *
     * @return The CustomerProductTableModel used by the JTable.
     */
    public CustomerProductTableModel getTableModel() {
        return tableModel;
    }

    /**
     * Initializes the components of the customer panel.
     * This includes the product table, the quantity text field, and the "Add to Cart" button.
     */
    private void init() {
        tableModel = new CustomerProductTableModel();
        productTable = new JTable(tableModel);
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        textField = new JTextField(5);
        label = new JLabel("Quantity of products: ");

        btnAddToCart = new JButton("Add to Cart");
        btnAddToCart.addActionListener(e -> controller.addToCartAction());
        buttonPanel.add(label);
        buttonPanel.add(textField);
        buttonPanel.add(btnAddToCart);
    }
}
