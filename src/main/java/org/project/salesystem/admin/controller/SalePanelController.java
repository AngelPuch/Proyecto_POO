package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.gui.SalePanel;
import org.project.salesystem.customer.dao.implementation.SaleDAOImpl;
import org.project.salesystem.customer.model.Sale;

import java.util.ArrayList;
import java.util.List;

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

    public void filterSaleListAction() {
        String searchText = salePanel.getSearchField().getText().trim();
        List<Sale> filteredSaleList = new ArrayList<>();
        for (Sale sale: saleDAO.readAll()) {
            if (sale.getCustomer().getName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredSaleList.add(sale);
            }
        }
        salePanel.setMessage("");

        if (filteredSaleList.isEmpty()){
            salePanel.setMessage("No se encontraron coincidencias");
            saleTableModel.showFilteredList(new ArrayList<>());
        }else{
            saleTableModel.showFilteredList(filteredSaleList);
        }
    }


}
