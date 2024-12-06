package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.gui.CategoryPanel;
import org.project.salesystem.admin.model.Category;

import javax.swing.*;

public class CategoryPanelController {
    private CategoryPanel categoryPanel;
    private CategoryTableModel categoryTableModel;

    public CategoryPanelController(CategoryPanel categoryPanel, CategoryTableModel categoryTableModel) {
        this.categoryPanel = categoryPanel;
        this.categoryTableModel = categoryTableModel;
    }

    public void addCategoryAction() {
        if (!categoryPanel.getNameField().getText().isEmpty()) {
            Category category = new Category();
            category.setName(categoryPanel.getNameField().getText());
            category.setDescription(categoryPanel.getDescriptionField().getText());
            categoryTableModel.addCategory(category);
            clearField();
        }else {
            JOptionPane.showMessageDialog(categoryPanel, "El campo nombre es obligatorio");
        }
    }

    public void deleteCategoryAction() {
        int selectedRow = categoryPanel.getTable().getSelectedRow();
        if (selectedRow != -1) {
            categoryTableModel.removeCategory(selectedRow);
        }else {
            JOptionPane.showMessageDialog(categoryPanel, "Selecciona una fila para eliminar");
        }
    }

    private void clearField() {
        categoryPanel.getNameField().setText("");
        categoryPanel.getDescriptionField().setText("");
    }

}
