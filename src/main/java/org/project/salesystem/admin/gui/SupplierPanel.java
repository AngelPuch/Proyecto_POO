package org.project.salesystem.admin.gui;

import org.project.salesystem.admin.controller.SupplierPanelController;
import org.project.salesystem.admin.controller.SupplierTableModel;

import javax.swing.*;
import java.awt.*;

public class SupplierPanel extends JPanel {
    private SupplierTableModel supplierTableModel;
    private JTable table;
    private JPanel inputPanel;
    private JTextField nameField;
    private  JTextField numberField;
    private SupplierPanelController controller;

    public SupplierPanel (){
        initComponents();
        this.controller = new SupplierPanelController(this, supplierTableModel);
        setVisible(true);
    }

    public JTextField getNameField() { return nameField; }
    public JTextField getNumberField() { return numberField; }
    public JTable getTable() { return table; }

    private void initComponents() {
        supplierTableModel = new SupplierTableModel();
        table = new JTable(supplierTableModel);

        inputPanel = new JPanel(new FlowLayout());
        nameField = new JTextField(10);
        numberField = new JTextField(10);

        JButton addButton = new JButton("Agregar");
        JButton deleteButton = new JButton("Eliminar");

        inputPanel.add(new JLabel("Nombre"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Numero de teléfono"));
        inputPanel.add(numberField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        addButton.addActionListener(e -> controller.addSupplierAction());
        deleteButton.addActionListener(e -> controller.deleteSupplierAction());

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
