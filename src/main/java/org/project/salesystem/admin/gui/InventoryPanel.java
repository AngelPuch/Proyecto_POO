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
    private ProductTableModel productTableModel;
    private JTable table;
    private JPanel inputPanel;

    private JTextField nameField;
    private JTextField priceField;
    private JTextField stockField;
    private JTextField searchField;
    private JComboBox<Supplier> comboTypeSupplier;
    private JComboBox<Category> comboTypeCategory;
    private InventoryPanelController controller;

    public InventoryPanel() {
        setLayout(new BorderLayout());
        init();
        this.controller = new InventoryPanelController(this, productTableModel,table);
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
    }

    public JTextField getNameField() { return nameField; }
    public JTextField getPriceField() { return priceField; }
    public JTextField getStockField() { return stockField; }
    public JTextField getSearchField() { return searchField; }
    public JComboBox<Supplier> getComboTypeSupplier() { return comboTypeSupplier; }
    public JComboBox<Category> getComboTypeCategory() { return comboTypeCategory; }

    private void init() {
        productTableModel = new ProductTableModel();
        table = new JTable(productTableModel);

        TableColumn supplierColumn = table.getColumnModel().getColumn(3);
        supplierColumn.setCellEditor(new DefaultCellEditor(new ProductTableModel().getComboTypeSupplier()));

        TableColumn categoryColumn = table.getColumnModel().getColumn(4);
        categoryColumn.setCellEditor(new DefaultCellEditor(new ProductTableModel().getComboTypeCategory()));

        inputPanel = new JPanel(new FlowLayout());
        nameField = new JTextField(8);
        priceField = new JTextField(8);
        stockField = new JTextField(8);
        searchField = new JTextField(8);
        JButton addButton = new JButton("Agregar");
        JButton deleteButton = new JButton("Eliminar");

        comboTypeSupplier = new JComboBox<>();
        comboTypeCategory = new JComboBox<>();
        fillComboBoxSupplier(comboTypeSupplier);
        fillComboBoxCategory(comboTypeCategory);

        inputPanel.add(new JLabel("Nombre:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Precio:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Stock:"));
        inputPanel.add(stockField);
        inputPanel.add(new JLabel("Proveedor"));
        inputPanel.add(comboTypeSupplier);
        inputPanel.add(new JLabel("Categoría"));
        inputPanel.add(comboTypeCategory);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        inputPanel.add(new JLabel("Buscar: "));
        inputPanel.add(searchField);


        addButton.addActionListener(e -> controller.actionAddProduct());
        deleteButton.addActionListener(e -> controller.actionDeleteProduct());
        searchField.addActionListener(e ->controller.actionFilterProductList());


    }
}
