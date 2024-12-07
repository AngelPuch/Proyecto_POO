package org.project.salesystem.admin.gui;

import org.project.salesystem.admin.controller.SupplierPanelController;
import org.project.salesystem.admin.controller.SupplierTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the GUI panel for managing suppliers
 * This panel allows adding, deleting, and filtering suppliers through a table and input fields
 */

public class SupplierPanel extends JPanel {
    private SupplierTableModel supplierTableModel;
    private JTable table;
    private JPanel inputPanel;
    private JTextField nameField;
    private  JTextField numberField;
    private JTextField searchField;
    private JLabel messageLabel;
    private SupplierPanelController controller;

    public SupplierPanel (){
        initComponents();
        this.controller = new SupplierPanelController(this, supplierTableModel);
        setVisible(true);
    }

    public JTextField getNameField() { return nameField; }
    public JTextField getNumberField() { return numberField; }
    public JTextField getSearchField() { return searchField; }
    public JTable getTable() { return table; }

    /**
     * Sets the message label to display a message
     * @param message the message to display
     */
    public void setMessage(String message) {
        this.messageLabel.setText(message);
    }

    /**
     * Initializes all components of the panel
     * Sets up the layout, input fields, table, and buttons
     */
    private void initComponents() {
        supplierTableModel = new SupplierTableModel();
        table = new JTable(supplierTableModel);

        inputPanel = new JPanel(new FlowLayout());
        nameField = new JTextField(10);
        numberField = new JTextField(10);
        searchField = new JTextField(10);
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.red);

        JButton addButton = new JButton("Agregar");
        JButton deleteButton = new JButton("Eliminar");

        inputPanel.add(new JLabel("Nombre"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Numero de teléfono"));
        inputPanel.add(numberField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        inputPanel.add(new JLabel("Buscar: "));
        inputPanel.add(searchField);

        addButton.addActionListener(e -> controller.addSupplierAction());
        deleteButton.addActionListener(e -> controller.deleteSupplierAction());
        searchField.addActionListener(e -> controller.filterSupplierListAction());

        setLayout(new BorderLayout());
        add(messageLabel, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
