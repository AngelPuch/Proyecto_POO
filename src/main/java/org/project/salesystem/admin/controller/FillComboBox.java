package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.implementation.CategoryDAOImpl;
import org.project.salesystem.admin.dao.implementation.SupplierDAOImpl;
import org.project.salesystem.admin.model.Category;
import org.project.salesystem.admin.model.Supplier;

import javax.swing.*;
import java.util.List;

public class FillComboBox {
    private static SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
    private static CategoryDAOImpl categoryDAO = new CategoryDAOImpl();


    public static void fillComboBoxSupplier(JComboBox<Supplier> comboTypeSupplier) {
        List<Supplier> supplierList;
        supplierList = supplierDAO.readAll();
        for (Supplier supplier: supplierList){
            comboTypeSupplier.addItem(supplier);
        }
    }

    public static void fillComboBoxCategory(JComboBox<Category> comboTypeCategory) {
        List<Category> categoryList;
        categoryList = categoryDAO.readAll();
        for (Category category: categoryList){
            comboTypeCategory.addItem(category);
        }
    }

}
