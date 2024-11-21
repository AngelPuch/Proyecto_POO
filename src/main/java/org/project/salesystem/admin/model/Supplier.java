package org.project.salesystem.admin.model;

import java.io.Serializable;

public class Supplier implements Serializable {
    private int supplierId;
    private String name;
    private String phone;

    public Supplier() {
    }

    public Supplier(int supplierId, String name, String phone) {
        this.supplierId = supplierId;
        this.name = name;
        this.phone = phone;
    }

    public int getId() { return supplierId; }

    public String getName() { return name; }

    public String getPhone() { return phone; }


    public void setId(int supplierId) { this.supplierId = supplierId; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
