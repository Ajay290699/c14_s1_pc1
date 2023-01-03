package com.niit.demoSpringUsingMongoDB.service;

import com.niit.demoSpringUsingMongoDB.domain.Customer;
import com.niit.demoSpringUsingMongoDB.exception.CustomerAlreadyExistException;
import com.niit.demoSpringUsingMongoDB.exception.CustomerNotFoundException;

import javax.swing.text.DefaultEditorKit;
import java.util.List;

public interface ICustomerService {

    public Customer addCustomer(Customer customer) throws CustomerAlreadyExistException;
    public List<Customer> getAllCustomer();
    public String deleteCustomer(int id) throws CustomerNotFoundException;
    public List<Customer> getCustomerByName(String customername);
    public List<Customer> getCustomerByCity(String city);

}
