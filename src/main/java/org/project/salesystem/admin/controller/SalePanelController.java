package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.gui.SalePanel;
import org.project.salesystem.customer.dao.implementation.SaleDAOImpl;
import org.project.salesystem.customer.model.Sale;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class to handle business logic for the {@link SalePanel}
 * It manages the filtering and printing of sales details
 */

public class SalePanelController {
    private SaleTableModel saleTableModel;
    private SalePanel salePanel;
    private SaleDAOImpl saleDAO;

    public SalePanelController(SalePanel salePanel, SaleTableModel saleTableModel) {
        this.salePanel = salePanel;
        this.saleTableModel = saleTableModel;
        this.saleDAO = new SaleDAOImpl();
    }

    public void printSaleDetail() {
        System.out.println("Detalle");
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
            saleTableModel.showFilteredList(new ArrayList<>());
        }else{
            salePanel.setMessage("");
            saleTableModel.showFilteredList(filteredSaleList);
        }
    }


}
