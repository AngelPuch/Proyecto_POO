package org.project.salesystem.admin.gui;

import org.project.salesystem.admin.controller.ProductPanelController;
import org.project.salesystem.admin.controller.ProductTableModel;
import org.project.salesystem.admin.model.Category;
import org.project.salesystem.admin.model.Supplier;
import static org.project.salesystem.admin.controller.FillComboBox.*;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * This class is a GUI component that represents the product management interface in the admin section of the sales system
 * It contains various input fields for managing product details like name, price, stock, supplier, and category
 * It also includes buttons for adding and removing products, as well as a table to display product information
 */

public class ProductPanel extends JPanel {
    private ProductTableModel productTableModel;
    private JTable table;
    private JPanel inputPanel;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField stockField;
    private JTextField searchField;
    private JLabel messageLabel;
    private JComboBox<Supplier> comboTypeSupplier;
    private JComboBox<Category> comboTypeCategory;
    private ProductPanelController controller;

    public ProductPanel() {
        initComponents();
        this.controller = new ProductPanelController(this);
        setVisible(true);
    }

    public JTable getTable() { return table; }
    public JTextField getNameField() { return nameField; }
    public JTextField getPriceField() { return priceField; }
    public JTextField getStockField() { return stockField; }
    public JTextField getSearchField() { return searchField; }
    public ProductTableModel getProductTableModel() { return productTableModel; }

    /**
     * ets a message to be displayed
     * @param message the message to display
     */
    public void setMessage(String message) {
        this.messageLabel.setText(message);
    }
    public JComboBox<Supplier> getComboTypeSupplier() { return comboTypeSupplier; }
    public JComboBox<Category> getComboTypeCategory() { return comboTypeCategory; }

    /**
     * Initializes the GUI components and sets up event listeners for product actions
     */
    private void initComponents() {
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
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.red);

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


        addButton.addActionListener(e -> controller.addProductAction());
        deleteButton.addActionListener(e -> controller.deleteProductAction());
        searchField.addActionListener(e ->controller.filterProductListAction());

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(messageLabel, BorderLayout.SOUTH);

    }
}
