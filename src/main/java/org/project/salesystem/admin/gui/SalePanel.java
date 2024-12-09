package org.project.salesystem.admin.gui;

import org.project.salesystem.admin.controller.SalePanelController;
import org.project.salesystem.admin.controller.SaleTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the panel for displaying and managing sales information
 * It includes a table for listing sales, a search bar, and controls for performing actions
 */

public class SalePanel extends JPanel {
    private SaleTableModel saleTableModel;
    private JTable table;
    private JPanel inputPanel;
    private JTextField searchField;
    private JLabel messageLabel;
    private JButton printDetailButton;
    private SalePanelController controller;

    public SalePanel() {
        initComponents();
        this.controller = new SalePanelController(this);
        setVisible(true);
    }

    public JTextField getSearchField() { return searchField; }
    public JTable getTable() { return table; }
    public SaleTableModel getSaleTableModel() { return saleTableModel; }

    /**
     * Sets a message to be displayed to the user.
     * @param message the message to display.
     */
    public void setMessage(String message) {
        this.messageLabel.setText(message);
    }

    /**
     * Initializes the components of the panel, including the table, buttons, and layout
     */
    private void initComponents() {
        saleTableModel = new SaleTableModel();
        table = new JTable(saleTableModel);

        inputPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchField = new JTextField(10);
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.red);

        inputPanel.add(new JLabel("Buscar: "));
        printDetailButton = new JButton("Imprimir detalle");

        inputPanel.add(searchField);
        inputPanel.add(printDetailButton);

        searchField.addActionListener(e -> controller.filterSaleListAction());

        printDetailButton.addActionListener(e -> controller.printSaleDetailAction());

        setLayout(new BorderLayout());
        add(messageLabel, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
