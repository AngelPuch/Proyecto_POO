package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.ProductDAO;
import org.project.salesystem.admin.dao.implementation.ProductDAOImpl;
import org.project.salesystem.admin.gui.CategoryPanel;
import org.project.salesystem.admin.model.Category;

import javax.swing.*;

/**
 * Controller for handling actions in the {@link CategoryPanel}
 * It interacts with the {@link CategoryTableModel} to add and delete categories from the database
 */

public class CategoryPanelController {
    private CategoryPanel categoryPanel;

    public CategoryPanelController(CategoryPanel categoryPanel) {
        this.categoryPanel = categoryPanel;
    }

    /**
     * Handles the action of adding a new category to the database
     * Validates the name field before adding the category
     */
    public void addCategoryAction() {
        if (!categoryPanel.getNameField().getText().trim().isEmpty()) {
            Category category = new Category();
            category.setName(categoryPanel.getNameField().getText());
            category.setDescription(categoryPanel.getDescriptionField().getText());
            categoryPanel.getCategoryTableModel().addCategory(category);
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
            int categoryID = categoryPanel.getCategoryTableModel().getCategoryList().get(selectedRow).getId();
            ProductDAO productDAO = new ProductDAOImpl();
            if (productDAO.hasProductsAssociatedWithCategory(categoryID)) {
                JOptionPane.showMessageDialog(categoryPanel,
                        "No se puede eliminar la categoría porque esta asociado a un producto");
            }else {
                categoryPanel.getCategoryTableModel().removeCategory(selectedRow);
            }
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
