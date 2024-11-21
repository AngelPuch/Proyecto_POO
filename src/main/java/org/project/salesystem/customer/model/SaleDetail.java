package org.project.salesystem.customer.model;

import org.project.salesystem.admin.model.Product;

import java.io.Serializable;

public class SaleDetail implements Serializable {
    private int SaleDetalId;
    private int quantity;
    private double productTotal;
    private Sale sale;
    private Product product;

    public SaleDetail() {
    }

    public SaleDetail(int saleDetalId, int quantity, double productTotal, Sale sale, Product product) {
        SaleDetalId = saleDetalId;
        this.quantity = quantity;
        this.productTotal = productTotal;
        this.sale = sale;
        this.product = product;
    }

    public int getSaleDetalId() { return SaleDetalId; }

    public int getQuantity() { return quantity; }

    public double getProductTotal() { return productTotal; }

    public Sale getSale() { return sale; }

    public Product getProduct() { return product; }


    public void setSaleDetalId(int saleDetalId) {
        SaleDetalId = saleDetalId;
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
