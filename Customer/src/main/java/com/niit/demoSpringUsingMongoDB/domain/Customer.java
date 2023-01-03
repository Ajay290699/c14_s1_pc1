package com.niit.demoSpringUsingMongoDB.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Customer {

    @Id
    private int customerId;
    private String customername;
    private long phoneNo;
    private Address address;

    public Customer() {
    }

    public Customer(int customerId, String customername, long phoneNo, Address address) {
        this.customerId = customerId;
        this.customername = customername;
        this.phoneNo = phoneNo;
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customername='" + customername + '\'' +
                ", phoneNo=" + phoneNo +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && phoneNo == customer.phoneNo && Objects.equals(customername, customer.customername) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customername, phoneNo, address);
    }
}
