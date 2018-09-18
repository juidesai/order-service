package com.solstice.orderservice.com.solstice.orderservice.Domain;


import javax.persistence.*;

public class Address {
    private long addressId;
    private String street;
    private String apt;
    private String city;
    private String state;
    private long zipcode;
    private String country;
    public Address(){}

    public Address(long addressId, String street, String apt, String city, String state, long zipcode, String country) {
        this.addressId = addressId;
        this.street = street;
        this.apt = apt;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getZipcode() {
        return zipcode;
    }

    public void setZipcode(long zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
