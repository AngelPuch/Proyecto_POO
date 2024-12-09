package org.project.salesystem.customer.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Represents a sale transaction made by a customer in the system.
 * It contains details about the sale, the customer who made the purchase, and the items involved.
 */
public class Sale implements Serializable {
    private int saleId;
    private Date dateOfSale;
    private double total;
    private Customer customer;


    public Sale() {
    }

    /**
     * Constructor to create a Sale with specified details.
     *
     * @param saleId The unique ID of the sale.
     * @param dateOfSale The date when the sale was made.
     * @param total The total amount of the sale.
     * @param customer The customer who made the sale.
     */
    public Sale(int saleId, Date dateOfSale, double total, Customer customer) {
        this.saleId = saleId;
        this.dateOfSale = dateOfSale;
        this.total = total;
        this.customer = customer;
    }


    public Sale(int saleId) {
        this.saleId = saleId;
    }


    public int getSaleId() {
        return saleId;
    }

    public Date getDateOfSale() {
        return dateOfSale;
    }

    public double getTotal() {
        return total;
    }

    public Customer getCustomer() {
        return customer;
    }


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
