package org.project.salesystem.admin.gui;

import org.project.salesystem.admin.controller.InventoryPanelController;
import org.project.salesystem.admin.controller.ProductTableModel;
import org.project.salesystem.admin.model.Category;
import org.project.salesystem.admin.model.Supplier;
import static org.project.salesystem.admin.gui.FillComboBox.*;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class InventoryPanel extends JPanel {
    private ProductTableModel tableModel;
    private JTable table;
    private JPanel inputPanel;

    private JTextField txtName;
    private JTextField txtPrice;
    private JTextField txtStock;
    private JComboBox<Supplier> comboTypeSupplier;
    private JComboBox<Category> comboTypeCategory;
    private InventoryPanelController controller;

    public InventoryPanel() {
        setLayout(new BorderLayout());
        init();
        this.controller = new InventoryPanelController(this, tableModel ,table);
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
    }

    public JTextField getTxtName() { return txtName; }
    public JTextField getTxtPrice() { return txtPrice; }
    public JTextField getTxtStock() { return txtStock; }
    public JComboBox<Supplier> getComboTypeSupplier() { return comboTypeSupplier; }
    public JComboBox<Category> getComboTypeCategory() { return comboTypeCategory; }

    private void init() {
        tableModel = new ProductTableModel();
        table = new JTable(tableModel);

        TableColumn supplierColumn = table.getColumnModel().getColumn(3);
        supplierColumn.setCellEditor(new DefaultCellEditor(new ProductTableModel().getComboTypeSupplier()));

        TableColumn categoryColumn = table.getColumnModel().getColumn(4);
        categoryColumn.setCellEditor(new DefaultCellEditor(new ProductTableModel().getComboTypeCategory()));


        inputPanel = new JPanel(new FlowLayout());
        txtName = new JTextField(10);
        txtPrice = new JTextField(10);
        txtStock = new JTextField(10);

        comboTypeSupplier = new JComboBox<>();
        comboTypeCategory = new JComboBox<>();
        fillComboBoxSupplier(comboTypeSupplier);
        fillComboBoxCategory(comboTypeCategory);

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
