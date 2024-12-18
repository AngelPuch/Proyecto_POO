package org.project.salesystem.customer.model;

import org.project.salesystem.admin.model.Admin;
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


    public int getSaleDetailId() {
        return SaleDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getProductTotal() {
        return productTotal;
    }

    public Sale getSale() {
        return sale;
    }

    public Product getProduct() {
        return product;
    }



    public void setSaleDetailId(int SaleDetailId) {
        this.SaleDetailId = SaleDetailId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductTotal(double productTotal) {
        this.productTotal = productTotal;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

