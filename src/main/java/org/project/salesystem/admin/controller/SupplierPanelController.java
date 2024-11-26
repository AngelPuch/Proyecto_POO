package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.dao.implementation.SupplierDAOImpl;
import org.project.salesystem.admin.gui.SupplierPanel;
import org.project.salesystem.admin.model.Supplier;

import javax.swing.*;

public class SupplierPanelController {
    private SupplierPanel supplierPanel;
    private SupplierTableModel supplierTableModel;
    private JTable table;
    private SupplierDAOImpl supplierDAO;

    public SupplierPanelController(SupplierPanel supplierPanel, SupplierTableModel supplierTableModel, JTable table) {
        this.supplierPanel = supplierPanel;
        this.supplierTableModel = supplierTableModel;
        this.table = table;
        supplierDAO = new SupplierDAOImpl();
    }

    public void actionAddSupplier() {
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

    public void actionDeleteSupplier() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1){
            supplierTableModel.removeSupplier(selectedRow);
        }else{
            JOptionPane.showMessageDialog(supplierPanel, "Selecciona una fila para eliminar");
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
