package org.project.salesystem.admin.controller;

import org.junit.jupiter.api.Test;
import org.project.salesystem.admin.dao.implementation.CategoryDAOImpl;
import org.project.salesystem.admin.dao.implementation.SupplierDAOImpl;
import org.project.salesystem.admin.model.Category;
import org.project.salesystem.admin.model.Supplier;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FillComboBoxTest {

    @Test
    void testFillComboBoxSupplierNotNull() {
        JComboBox<Supplier> comboBoxSupplier = new JComboBox<>();

        FillComboBox.fillComboBoxSupplier(comboBoxSupplier);

        assertNotNull(comboBoxSupplier);
    }

    @Test
    void testFillComboBoxCategoryNotNull() {
        JComboBox<Category> comboBoxCategory = new JComboBox<>();

        FillComboBox.fillComboBoxCategory(comboBoxCategory);

        assertNotNull(comboBoxCategory);
    }

    @Test
    void testFillComboBoxSupplierEquals() {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        List<Supplier> supplierList = supplierDAO.readAll();

        JComboBox<Supplier> comboBoxSupplier = new JComboBox<>();
        FillComboBox.fillComboBoxSupplier(comboBoxSupplier);

        assertNotNull(comboBoxSupplier);

        for (int i = 0; i < supplierList.size(); i++) {
            assertEquals(supplierList.get(i).getName(), comboBoxSupplier.getItemAt(i).getName());
        }

    }

    @Test
    void testFillComboBoxCategoryEquals() {
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        List<Category> categoryList = categoryDAO.readAll();

        JComboBox<Category> comboBoxCategory = new JComboBox<>();
        FillComboBox.fillComboBoxCategory(comboBoxCategory);

        assertNotNull(comboBoxCategory);

        for (int i = 0; i < categoryList.size(); i++) {
            assertEquals(categoryList.get(i).getName(), comboBoxCategory.getItemAt(i).getName());
        }

    }



}