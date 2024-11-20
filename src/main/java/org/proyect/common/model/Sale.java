package org.proyect.common.model;

import org.proyect.sale.model.Client;

import java.io.Serializable;
import java.util.Date;

public class Sale implements Serializable {
    private int id;
    private Client client;
    private Product product;
    private double total;
    private Date dateOfSale;

    public Sale() {
    }

    public Sale(int id, Client client, Product product, double total, Date dateOfSale) {
        this.id = id;
        this.client = client;
        this.product = product;
        this.total = total;
        this.dateOfSale = dateOfSale;
    }

    public int getId() { return id; }
    public Client getClient() { return client; }
    public Product getProduct() { return product; }
    public double getTotal() { return total; }
    public Date getDateOfSale() { return dateOfSale; }

    public void setId(int id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
    }
}
