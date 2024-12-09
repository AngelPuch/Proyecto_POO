package org.project.salesystem.customer.model;

import java.io.Serializable;

/**
 * Represents a customer in the system.
 * A customer has personal details such as name, contact information, address, and credentials.
 */
public class Customer implements Serializable {
    private int customerId;
    private String name;
    private String phoneNumber;
    private String username;
    private String password;
    private String street;
    private String postal_code;
    private String city;
    private String state;

    public Customer() {
    }

    /**
     * Constructor to create a Customer with specified details.
     *
     * @param customerId The unique ID of the customer.
     * @param name The name of the customer.
     * @param phoneNumber The phone number of the customer.
     * @param username The username for the customer.
     * @param password The password for the customer.
     * @param street The street address of the customer.
     * @param postal_code The postal code of the customer's address.
     * @param city The city of the customer's address.
     * @param state The state of the customer's address.
     */
    public Customer(int customerId, String name, String phoneNumber, String username, String password, String street, String postal_code, String city, String state) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.street = street;
        this.postal_code = postal_code;
        this.city = city;
        this.state = state;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStreet() {
        return street;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }


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

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }
}
