package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.gui.CategoryPanel;
import org.project.salesystem.admin.model.Category;

import javax.swing.*;

/**
 * Controller for handling actions in the {@link CategoryPanel}
 * It interacts with the {@link CategoryTableModel} to add and delete categories from the database
 */

public class CategoryPanelController {
    private CategoryPanel categoryPanel;
    private CategoryTableModel categoryTableModel;

    public CategoryPanelController(CategoryPanel categoryPanel, CategoryTableModel categoryTableModel) {
        this.categoryPanel = categoryPanel;
        this.categoryTableModel = categoryTableModel;
    }

    /**
     * Handles the action of adding a new category to the database
     * Validates the name field before adding the category
     */
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

    /**
     * Handles the action of deleting a selected category from the database
     */
    public void deleteCategoryAction() {
        int selectedRow = categoryPanel.getTable().getSelectedRow();
        if (selectedRow != -1) {
            categoryTableModel.removeCategory(selectedRow);
        }else {
            JOptionPane.showMessageDialog(categoryPanel, "Selecciona una fila para eliminar");
        }
    }

    /**
     * Clears the name and description fields after adding a category
     */
    private void clearField() {
        categoryPanel.getNameField().setText("");
        categoryPanel.getDescriptionField().setText("");
    }

}
