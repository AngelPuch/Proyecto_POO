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
    private List<SaleDetail> details;

    /**
     * Default constructor for Sale.
     */
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

    /**
     * Constructor to create a Sale with specified sale ID.
     *
     * @param saleId The unique ID of the sale.
     */
    public Sale(int saleId) {
        this.saleId = saleId;
    }

    /**
     * Gets the list of sale details (products purchased).
     *
     * @return The list of SaleDetail objects representing the items in the sale.
     */
    public List<SaleDetail> getDetails() {
        return details;
    }

    /**
     * Gets the unique identifier of the sale.
     *
     * @return The sale ID.
     */
    public int getSaleId() {
        return saleId;
    }

    /**
     * Gets the date when the sale occurred.
     *
     * @return The date of the sale.
     */
    public Date getDateOfSale() {
        return dateOfSale;
    }

    /**
     * Gets the total amount of the sale.
     *
     * @return The total amount of the sale.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Gets the customer who made the sale.
     *
     * @return The customer associated with the sale.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the list of sale details (products purchased).
     *
     * @param details The list of SaleDetail objects representing the items in the sale.
     */
    public void setDetails(List<SaleDetail> details) {
        this.details = details;
    }

    /**
     * Sets the unique identifier of the sale.
     *
     * @param saleId The sale ID.
     */
    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    /**
     * Sets the date when the sale occurred.
     *
     * @param dateOfSale The date of the sale.
     */
    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    /**
     * Sets the total amount of the sale.
     *
     * @param total The total amount of the sale.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Sets the customer who made the sale.
     *
     * @param customer The customer associated with the sale.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
