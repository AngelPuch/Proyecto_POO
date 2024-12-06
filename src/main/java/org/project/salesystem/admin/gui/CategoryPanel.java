package org.project.salesystem.admin.gui;

import org.project.salesystem.admin.controller.CategoryPanelController;
import org.project.salesystem.admin.controller.CategoryTableModel;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends JPanel {
    private CategoryTableModel categoryTableModel;
    private JTable table;
    private JPanel inputPanel;
    private JTextField nameField;
    private  JTextArea descriptionField;
    private CategoryPanelController controller;

    public CategoryPanel() {
        initComponents();
        this.controller = new CategoryPanelController(this, categoryTableModel);
        setVisible(true);
    }

    public JTable getTable() { return table; }
    public JTextField getNameField() { return nameField; }
    public JTextArea getDescriptionField() { return descriptionField; }

    private void initComponents() {
        categoryTableModel = new CategoryTableModel();
        table = new JTable(categoryTableModel);

        inputPanel = new JPanel(new FlowLayout());
        nameField = new JTextField(10);
        descriptionField = new JTextArea(1, 10);

        JButton addButton = new JButton("Agregar");
        JButton deleteButton = new JButton("Eliminar");

        inputPanel.add(new JLabel("Nombre"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Descripción"));
        inputPanel.add(descriptionField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        addButton.addActionListener(e -> controller.addCategoryAction());
        deleteButton.addActionListener(e -> controller.deleteCategoryAction());

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

    }
}
