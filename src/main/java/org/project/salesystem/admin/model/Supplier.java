package org.project.salesystem.admin.model;

import java.io.Serializable;

/**
 * Represents a supplier in the system
 * This class contains information about the supplier such as its name and phone number
 */

public class Supplier implements Serializable {

    /** The unique identifier for the supplier */
    private int supplierId;

    /** The name of the supplier */
    private String name;

    /** The phone number of the supplier */
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


    public void setId(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name;
    }
}
