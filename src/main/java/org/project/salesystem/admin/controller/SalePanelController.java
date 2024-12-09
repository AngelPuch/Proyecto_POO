package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.gui.SalePanel;
import org.project.salesystem.customer.dao.implementation.SaleDAOImpl;
import org.project.salesystem.customer.model.Sale;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class to handle business logic for the {@link SalePanel}
 * It manages the filtering and printing of sales details
 */

public class SalePanelController {
    private SalePanel salePanel;
    private SaleDAOImpl saleDAO;

    public SalePanelController(SalePanel salePanel) {
        this.salePanel = salePanel;
        this.saleDAO = new SaleDAOImpl();
    }

    public void printSaleDetailAction() {
        int selectedRow = salePanel.getTable().getSelectedRow();
        if (selectedRow != -1) {
            salePanel.getSaleTableModel().printSaleDetail(selectedRow);
        }else {
            JOptionPane.showMessageDialog(salePanel, "Seleccione una venta para mostrar los detalles");
        }
    }

    /**
     * Filters the sales list based on the search text entered by the user
     */
    public void filterSaleListAction() {
        String searchText = salePanel.getSearchField().getText().trim();
        List<Sale> filteredSaleList = new ArrayList<>();
        for (Sale sale: saleDAO.readAll()) {
            if (sale.getCustomer().getName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredSaleList.add(sale);
            }
        }

        if (filteredSaleList.isEmpty()){
            salePanel.setMessage("No se encontraron coincidencias");
            salePanel.getSaleTableModel().showFilteredList(new ArrayList<>());
        }else{
            salePanel.setMessage("");
            salePanel.getSaleTableModel().showFilteredList(filteredSaleList);
        }
    }


}
