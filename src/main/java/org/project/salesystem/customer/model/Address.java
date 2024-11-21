package org.project.salesystem.customer.model;

import java.io.Serializable;

public class Address implements Serializable {
    private int addressId;
    private String postalCode;
    private String street;
    private int number;
    private String city;
    private String state;
    private String country;

    public Address() {
    }

    public Address(int addressId, String postalCode, String street, int number, String city,
                   String state, String country) {
        this.addressId = addressId;
        this.postalCode = postalCode;
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public int getAddressId() { return addressId; }

    public String getPostalCode() { return postalCode; }

    public String getStreet() { return street; }

    public int getNumber() { return number; }

    public String getCity() { return city; }

    public String getState() { return state; }

    public String getCountry() { return country; }


    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
