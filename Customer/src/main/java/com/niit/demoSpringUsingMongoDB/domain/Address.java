package com.niit.demoSpringUsingMongoDB.domain;

import java.util.Objects;

public class Address {

    private int pincode;
    private String city;

    public Address() {
    }

    public Address(int pincode, String city) {
        this.pincode = pincode;
        this.city = city;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "pincode=" + pincode +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return pincode == address.pincode && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pincode, city);
    }
}
