package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.implementation.CategoryDAOImpl;
import org.project.salesystem.admin.model.Category;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Table model for displaying categories in a JTable
 * Categories are stored and managed in a database
 * Manages adding, removing, and updating categories in the database
 */

public class CategoryTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nombre", "Descripción"};
    private CategoryDAOImpl categoryDAO;
    private List<Category> categoryList;

    /**
     * Constructs the CategoryTableModel and loads categories from the database
     * Initializes the {@link CategoryDAOImpl} to interact with the database
     */
    public CategoryTableModel() {
        this.categoryDAO = new CategoryDAOImpl();
        categoryList = categoryDAO.readAll();
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    @Override
    public int getRowCount() {
        return categoryList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

   @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category category = categoryList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> category.getName();
            case 1 -> category.getDescription();
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Category category = categoryList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                category.setName(aValue.toString());
                break;
            case 1:
                category.setDescription(aValue.toString());
                break;
        }
        categoryDAO.update(category);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    /**
     * Adds a new {@link Category} to the database and updates the table view
     * @param category the {@link Category} to add
     */
    public void addCategory(Category category) {
        categoryDAO.create(category);
        refreshCategoryList();
        fireTableRowsInserted(categoryList.size() - 1, categoryList.size() - 1);
    }

    /**
     * Removes the category at the specified row from the database and updates the table
     * @param rowIndex the index of the {@link Category} to remove
     */
    public void removeCategory(int rowIndex) {
        categoryDAO.delete(categoryList.get(rowIndex).getId());
        refreshCategoryList();
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    /**
     * Refreshes the list of categories by reloading data from the database
     */
    private void refreshCategoryList() {
        categoryList = categoryDAO.readAll();
    }

}
