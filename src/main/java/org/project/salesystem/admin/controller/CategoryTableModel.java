package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.implementation.CategoryDAOImpl;
import org.project.salesystem.admin.model.Category;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CategoryTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nombre", "Descripción"};
    private CategoryDAOImpl categoryDAO;
    private List<Category> categoryList;

    public CategoryTableModel() {
        this.categoryDAO = new CategoryDAOImpl();
        categoryList = categoryDAO.readAll();
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

    public void addCategory(Category category) {
        categoryDAO.create(category);
        refreshCategoryList();
        fireTableRowsInserted(categoryList.size() - 1, categoryList.size() - 1);
    }

    public void removeCategory(int rowIndex) {
        categoryDAO.delete(categoryList.get(rowIndex).getId());
        refreshCategoryList();
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    private void refreshCategoryList() {
        categoryList = categoryDAO.readAll();
    }

}
