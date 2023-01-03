package com.niit.demoSpringUsingMongoDB.service;

import com.niit.demoSpringUsingMongoDB.domain.Customer;
import com.niit.demoSpringUsingMongoDB.exception.CustomerAlreadyExistException;
import com.niit.demoSpringUsingMongoDB.exception.CustomerNotFoundException;
import com.niit.demoSpringUsingMongoDB.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService{

    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addCustomer(Customer customer) throws CustomerAlreadyExistException {
        if (customerRepository.findById(customer.getCustomerId()).isEmpty()) {
            return customerRepository.save(customer);
        }
        throw new  CustomerAlreadyExistException();
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public String deleteCustomer(int id) throws CustomerNotFoundException {
        if (customerRepository.findById(id).isEmpty()){
            throw new CustomerNotFoundException();
        }
        return "Customer Deleted succefully";
    }

    @Override
    public List<Customer> getCustomerByName(String customername) {
        return customerRepository.findBycustomername(customername);
    }

    @Override
    public List<Customer> getCustomerByCity(String city) {
        return customerRepository.findAllCustomerByCity(city);
    }
}
