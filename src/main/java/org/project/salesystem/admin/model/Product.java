package org.project.salesystem.admin.model;

import java.io.Serializable;

/**
 * Represents a product in the system
 * This class contains information about the product such as its name, price,
 * stock, category ans supplier
 */

public class Product implements Serializable {

    /** The unique identifier for the product*/
    private int productId;

    /** The name of the product */
    private String name;

    /** The price of the product */
    private double price;

    /** The stock quantity of the product */
    private int stock;

    /** The category to which the product belongs */
    private Category category;

    /** The supplier of the product */
    private Supplier supplier;

    public Product() {
    }

    public Product(int productId, String name, double price, int stock, Category category, Supplier supplier) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.supplier = supplier;
    }

    public int getId() { return productId; }

    public String getName() { return name; }

    public double getPrice() { return price; }

    public int getStock() { return stock; }

    public Category getCategory() { return category; }

    public Supplier getSupplier() { return supplier; }


    public void setId(int productId) { this.productId = productId; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
