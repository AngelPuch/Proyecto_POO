package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.implementation.SupplierDAOImpl;
import org.project.salesystem.admin.gui.SupplierPanel;
import org.project.salesystem.admin.model.Supplier;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierPanelController {
    private SupplierPanel supplierPanel;
    private SupplierTableModel supplierTableModel;
    private SupplierDAOImpl supplierDAO;

    public SupplierPanelController(SupplierPanel supplierPanel, SupplierTableModel supplierTableModel) {
        this.supplierPanel = supplierPanel;
        this.supplierTableModel = supplierTableModel;
        this.supplierDAO = new SupplierDAOImpl();
    }

    public void addSupplierAction() {
        if (validateNonEmptyField()) {
            Supplier supplier = new Supplier();
            supplier.setName(supplierPanel.getNameField().getText());
            supplier.setPhone(supplierPanel.getNumberField().getText());
            supplierTableModel.addSupplier(supplier);
            clearField();
        }else{
            JOptionPane.showMessageDialog(supplierPanel, "Todos los campos son obligatorios");
        }
    }

    public void deleteSupplierAction() {
        int selectedRow = supplierPanel.getTable().getSelectedRow();
        if (selectedRow != -1){
            supplierTableModel.removeSupplier(selectedRow);
        }else{
            JOptionPane.showMessageDialog(supplierPanel, "Selecciona una fila para eliminar");
        }
    }

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
            supplierTableModel.showFilteredList(new ArrayList<>());
        }else {
            supplierPanel.setMessage("");
            supplierTableModel.showFilteredList(filteredSupplierList);
        }
    }

    private boolean validateNonEmptyField() {
        return !supplierPanel.getNameField().getText().isEmpty() &&
                !supplierPanel.getNumberField().getText().isEmpty();
    }

    private void clearField () {
        supplierPanel.getNameField().setText("");
        supplierPanel.getNumberField().setText("");
    }
}
