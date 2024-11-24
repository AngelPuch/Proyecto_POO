package org.project.salesystem.admin.gui;

import org.project.salesystem.admin.controller.InventoryPanelController;
import org.project.salesystem.admin.model.Category;
import org.project.salesystem.admin.model.Supplier;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class InventoryPanel extends JPanel {
    private ProductTableModel tableModel;
    private JTable table;
    JPanel inputPanel;

    private JTextField txtName;
    private JTextField txtPrice;
    private JTextField txtStock;
    JComboBox<Supplier> comboTypeSupplier;
    JComboBox<Category> comboTypeCategory;
    private InventoryPanelController controller;

    public InventoryPanel() {
        setLayout(new BorderLayout());
        init();

        this.controller = new InventoryPanelController(this, tableModel ,table);
        controller.fillComboBoxSupplier();
        controller.fillComboBoxCategory();

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
    }

    public String getTxtName() { return txtName.getText(); }
    public String getTxtPrice() { return txtPrice.getText(); }
    public String getTxtStock() { return txtStock.getText(); }
    public JComboBox<Supplier> getComboTypeSupplier() { return comboTypeSupplier; }
    public JComboBox<Category> getComboTypeCategory() { return comboTypeCategory; }

    private void init() {
        tableModel = new ProductTableModel();
        table = new JTable(tableModel);
        TableColumn supplierColumn = table.getColumnModel().getColumn(3);
        supplierColumn.setCellEditor(new DefaultCellEditor(new ProductTableModel().comboTypeSupplier));

        TableColumn categoryColumn = table.getColumnModel().getColumn(4);
        categoryColumn.setCellEditor(new DefaultCellEditor(new ProductTableModel().comboTypeCategory));


        inputPanel = new JPanel(new FlowLayout());
        txtName = new JTextField(7);
        txtPrice = new JTextField(7);
        txtStock = new JTextField(7);

        comboTypeSupplier = new JComboBox<>();
        comboTypeCategory = new JComboBox<>();

        inputPanel.add(new JLabel("Nombre:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("Precio:"));
        inputPanel.add(txtPrice);
        inputPanel.add(new JLabel("Stock:"));
        inputPanel.add(txtStock);
        inputPanel.add(new JLabel("Proveedor"));
        inputPanel.add(comboTypeSupplier);
        inputPanel.add(new JLabel("Categoría"));
        inputPanel.add(comboTypeCategory);


        JButton btnAdd = new JButton("Agregar");
        JButton btnDelete = new JButton("Eliminar");
        btnAdd.addActionListener(e -> controller.actionAddProduct());
        btnDelete.addActionListener(e -> controller.actionDeleteProduct());

        inputPanel.add(btnAdd);
        inputPanel.add(btnDelete);

    }




}
