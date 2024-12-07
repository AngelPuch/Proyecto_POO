package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.implementation.CategoryDAOImpl;
import org.project.salesystem.admin.dao.implementation.SupplierDAOImpl;
import org.project.salesystem.admin.model.Category;
import org.project.salesystem.admin.model.Supplier;

import javax.swing.*;
import java.util.List;

/**
 * This class is responsible for populating {@code JComboBox}
 * components for suppliers and categories with data retrieved from the database.
 * It uses the corresponding DAOs to fetch the lists of suppliers and categories,
 * then adds them to the provided combo boxes.
 */

public class FillComboBox {
    private static SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
    private static CategoryDAOImpl categoryDAO = new CategoryDAOImpl();

    /**
     * Populates a {@code JComboBox<Supplier>} with suppliers retrieved from the database.
     * @param comboTypeSupplier the {@code JComboBox<Supplier>} to be populated with suppliers
     */
    public static void fillComboBoxSupplier(JComboBox<Supplier> comboTypeSupplier) {
        List<Supplier> supplierList;
        supplierList = supplierDAO.readAll();
        for (Supplier supplier: supplierList){
            comboTypeSupplier.addItem(supplier);
        }
    }

    /**
     * Populates a {@code JComboBox<Category>} with categories retrieved from the database
     * @param comboTypeCategory the {@code JComboBox<Category>} to be populated with categories
     */
    public static void fillComboBoxCategory(JComboBox<Category> comboTypeCategory) {
        List<Category> categoryList;
        categoryList = categoryDAO.readAll();
        for (Category category: categoryList){
            comboTypeCategory.addItem(category);
        }
    }

}
