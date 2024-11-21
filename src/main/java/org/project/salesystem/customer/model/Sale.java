package org.project.salesystem.customer.model;

import java.io.Serializable;
import java.util.Date;

public class Sale implements Serializable {
    private int saleId;
    private Date dateOfSale;
    private double total;
    private Customer customer;

    public Sale() {
    }

    public Sale(int saleId, Date dateOfSale, double total, Customer customer) {
        this.saleId = saleId;
        this.dateOfSale = dateOfSale;
        this.total = total;
        this.customer = customer;
    }

    public int getSaleId() { return saleId; }

    public Date getDateOfSale() { return dateOfSale; }

    public double getTotal() { return total; }

    public Customer getCustomer() { return customer; }


    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
