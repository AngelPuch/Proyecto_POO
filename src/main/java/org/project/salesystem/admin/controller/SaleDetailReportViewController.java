package org.project.salesystem.admin.controller;

import org.project.salesystem.admin.gui.SaleDetailReportView;
import org.project.salesystem.customer.dao.implementation.SaleDAOImpl;
import org.project.salesystem.customer.model.Sale;

public class SaleDetailReportViewController {
    SaleDetailReportView saleDetailReportView;
    SaleDAOImpl saleDAO;

    public SaleDetailReportViewController(SaleDetailReportView saleDetailReportView) {
        this.saleDetailReportView = saleDetailReportView;
        saleDAO = new SaleDAOImpl();
        populateField();
    }

    private void populateField() {
        Sale sale = saleDAO.read(saleDetailReportView.getIdSale());
        String address = sale.getCustomer().getStreet() + ", "
                + sale.getCustomer().getPostal_code() + ", "
                + sale.getCustomer().getCity() + ", "
                + sale.getCustomer().getState();
        saleDetailReportView.setNameLabel(sale.getCustomer().getName());
        saleDetailReportView.setPhoneNumberLabel(sale.getCustomer().getPhoneNumber());
        saleDetailReportView.setAddressLabel(address);
        saleDetailReportView.setDateLabel(sale.getDateOfSale());
        saleDetailReportView.setTotalLabel(String.valueOf(sale.getTotal()));
    }
}
