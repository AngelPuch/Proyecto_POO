package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.ProductDAO;
import org.project.salesystem.admin.dao.implementation.ProductDAOImpl;
import org.project.salesystem.admin.dao.implementation.SupplierDAOImpl;
import org.project.salesystem.admin.gui.SupplierPanel;
import org.project.salesystem.admin.model.Supplier;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the logic for the {@link SupplierPanel}
 * Connects the GUI with the underlying data operations
 */

public class SupplierPanelController {
    private SupplierPanel supplierPanel;
    private SupplierDAOImpl supplierDAO;

    public SupplierPanelController(SupplierPanel supplierPanel) {
        this.supplierPanel = supplierPanel;
        this.supplierDAO = new SupplierDAOImpl();
    }

    /**
     * Handles the action for adding a new supplier
     * Validates inputs and updates the table
     */
    public void addSupplierAction() {
        if (validateNonEmptyField()) {
            if (isValidPhoneNumber()) {
                Supplier supplier = new Supplier();
                supplier.setName(supplierPanel.getNameField().getText());
                supplier.setPhone(supplierPanel.getNumberField().getText());
                supplierPanel.getSupplierTableModel().addSupplier(supplier);
                clearField();
            }
        }else{
            JOptionPane.showMessageDialog(supplierPanel, "Todos los campos son obligatorios");
        }
    }

    /**
     * Handles the action for deleting a selected supplier
     * Removes the selected row from the table and updates the data
     */
    public void deleteSupplierAction() {
        int selectedRow = supplierPanel.getTable().getSelectedRow();
        if (selectedRow != -1){
            int supplierID = supplierPanel.getSupplierTableModel().getSupplierList().get(selectedRow).getId();
            ProductDAO productDAO = new ProductDAOImpl();
            if (productDAO.hasProductsAssociatedWithSupplier(supplierID)) {
                JOptionPane.showMessageDialog(supplierPanel,
                        "No se puede eliminar al proveedor porque esta asociado a un producto");
            }else{
                supplierPanel.getSupplierTableModel().removeSupplier(selectedRow);
            }
        }else{
            JOptionPane.showMessageDialog(supplierPanel, "Selecciona una fila para eliminar");
        }
    }

    /**
     * Filters the supplier list based on the search field
     * Displays a message if no matches are found
     */
    public void filterSupplierListAction() {
        String searchText = supplierPanel.getSearchField().getText().trim();
        List<Supplier> filteredSupplierList = new ArrayList<>();
        for (Supplier supplier: supplierDAO.readAll()) {
            if (supplier.getName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredSupplierList.add(supplier);
            }
        }

        if (filteredSupplierList.isEmpty()) {
            supplierPanel.setMessage("No se encontraron coincidencias");
            supplierPanel.getSupplierTableModel().showFilteredList(new ArrayList<>());
        }else {
            supplierPanel.setMessage("");
            supplierPanel.getSupplierTableModel().showFilteredList(filteredSupplierList);
        }
    }

    /**
     * Validates if the entered phone number is valid.
     * @return {@code true} if the phone number is valid (10 digits and numeric),
     *         {@code false} otherwise.
     */
    public boolean isValidPhoneNumber() {
        String phoneNumber = supplierPanel.getNumberField().getText();
        if (phoneNumber.length() != 10 || !phoneNumber.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(supplierPanel, "Debe ingresar un número de teléfono valido");
            return false;
        }
        return true;
    }

    /**
     * Validates non-empty input fields
     * @return {@code true} if all fields are filled, {@code false} otherwise
     */
    public boolean validateNonEmptyField() {
        return !supplierPanel.getNameField().getText().trim().isEmpty() &&
                !supplierPanel.getNumberField().getText().trim().isEmpty();
    }

    /**
     * Clears input fields
     */
    private void clearField () {
        supplierPanel.getNameField().setText("");
        supplierPanel.getNumberField().setText("");
    }
}
