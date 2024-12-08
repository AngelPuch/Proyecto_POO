package org.project.salesystem.customer.model;

import org.project.salesystem.admin.model.Product;

import java.io.Serializable;

/**
 * Represents a detailed item in a sale transaction.
 * It includes the quantity, the total price for the product,
 * and references to the related sale and product.
 */
public class SaleDetail implements Serializable {
    private int SaleDetailId;
    private int quantity;
    private double productTotal;
    private Sale sale;
    private Product product;

    /**
     * Default constructor for SaleDetail.
     */
    public SaleDetail() {
    }

    /**
     * Constructor to create a SaleDetail with specified details.
     *
     * @param SaleDetailId The unique ID of the sale detail.
     * @param quantity The quantity of the product in the sale.
     * @param productTotal The total price for the product in this sale detail.
     * @param sale The sale that this detail belongs to.
     * @param product The product involved in the sale.
     */
    public SaleDetail(int SaleDetailId, int quantity, double productTotal, Sale sale, Product product) {
        this.SaleDetailId = SaleDetailId;
        this.quantity = quantity;
        this.productTotal = productTotal;
        this.sale = sale;
        this.product = product;
    }

    /**
     * Gets the unique identifier of the sale detail.
     *
     * @return The sale detail ID.
     */
    public int getSaleDetailId() {
        return SaleDetailId;
    }

    /**
     * Gets the quantity of the product in the sale.
     *
     * @return The quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the total price for the product in this sale detail.
     *
     * @return The product's total price.
     */
    public double getProductTotal() {
        return productTotal;
    }

    /**
     * Gets the sale that this detail belongs to.
     *
     * @return The sale associated with this sale detail.
     */
    public Sale getSale() {
        return sale;
    }

    /**
     * Gets the product involved in this sale detail.
     *
     * @return The product associated with this sale detail.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the unique identifier of the sale detail.
     *
     * @param SaleDetailId The sale detail ID.
     */
    public void setSaleDetailId(int SaleDetailId) {
        this.SaleDetailId = SaleDetailId;
    }

    /**
     * Sets the quantity of the product in the sale.
     *
     * @param quantity The quantity of the product.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the total price for the product in this sale detail.
     *
     * @param productTotal The product's total price.
     */
    public void setProductTotal(double productTotal) {
        this.productTotal = productTotal;
    }

    /**
     * Sets the sale that this detail belongs to.
     *
     * @param sale The sale associated with this sale detail.
     */
    public void setSale(Sale sale) {
        this.sale = sale;
    }

    /**
     * Sets the product involved in this sale detail.
     *
     * @param product The product associated with this sale detail.
     */
    public void setProduct(Product product) {
        this.product = product;
    }
}

