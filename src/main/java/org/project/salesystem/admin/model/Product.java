package org.project.salesystem.admin.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int productId;
    private String name;
    private double price;
    private int stock;
    private Category category;
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

    public Product(int productId, String name, double price, int stock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
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
