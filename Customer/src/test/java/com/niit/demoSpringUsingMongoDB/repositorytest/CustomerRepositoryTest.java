package com.niit.demoSpringUsingMongoDB.repositorytest;

import com.niit.demoSpringUsingMongoDB.domain.Address;
import com.niit.demoSpringUsingMongoDB.domain.Customer;
import com.niit.demoSpringUsingMongoDB.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;
    Customer customer;
    Address address;

    @BeforeEach
    public void setUp(){
        this.address = new Address(251006,"pune");
        this.customer = new Customer(102,"Rakesh",4829895219l,address);
    }

    @Test
    public void addCustomer(){
        customerRepository.save(customer);
        Customer c1 = customerRepository.findById(customer.getCustomerId()).get();

        assertEquals(c1,customer);
    }

    @Test
    public void getAllCustomer(){
        customerRepository.delete(customer);
        customerRepository.save(customer);
        List<Customer> list = customerRepository.findAll();

        assertEquals(1,list.size());
    }

    @Test
    public void deleteCustomerById(){
        customerRepository.deleteAll();
        this.address = new Address(251006,"Mumbai");
        this.customer = new Customer(111,"Amit",482989545l,address);
        customerRepository.save(customer);
        Customer c1 = customerRepository.findById(customer.getCustomerId()).get();
        customerRepository.deleteById(customer.getCustomerId());

        assertEquals(Optional.empty(),customerRepository.findById(c1.getCustomerId()));
    }



    @AfterEach
    public void tearDown(){
        this.address = null;
        this.customer = null;
    }
}
