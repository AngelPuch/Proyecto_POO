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

    /**
     * Default constructor for Customer.
     */
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

    /**
     * Gets the unique identifier of the customer.
     *
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the unique identifier of the customer.
     *
     * @param customerId The customer ID.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the name of the customer.
     *
     * @return The customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name The name of the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the phone number of the customer.
     *
     * @return The customer's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param phoneNumber The customer's phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the username of the customer.
     *
     * @return The customer's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the customer.
     *
     * @param username The username of the customer.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the customer.
     *
     * @return The customer's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the customer.
     *
     * @param password The password of the customer.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the street address of the customer.
     *
     * @return The street address of the customer.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street address of the customer.
     *
     * @param street The street address of the customer.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the postal code of the customer's address.
     *
     * @return The postal code of the customer's address.
     */
    public String getPostal_code() {
        return postal_code;
    }

    /**
     * Sets the postal code of the customer's address.
     *
     * @param postal_code The postal code of the customer's address.
     */
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    /**
     * Gets the city of the customer's address.
     *
     * @return The city of the customer's address.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the customer's address.
     *
     * @param city The city of the customer's address.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the state of the customer's address.
     *
     * @return The state of the customer's address.
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state of the customer's address.
     *
     * @param state The state of the customer's address.
     */
    public void setState(String state) {
        this.state = state;
    }
}
