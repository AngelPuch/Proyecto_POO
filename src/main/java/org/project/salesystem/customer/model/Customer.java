package org.project.salesystem.customer.model;

import java.io.Serializable;

public class Customer implements Serializable {
    private int customerId;
    private String name;
    private String phoneNumber;
    private String username;
    private String password;
    private Address address;

    public Customer() {
    }

    public Customer(int customerId, String name, String phoneNumber, String username,
                    String password, Address address) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.address = address;
    }

    public Customer(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() { return customerId; }

    public String getName() { return name; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public Address getAddress() { return address; }


    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
