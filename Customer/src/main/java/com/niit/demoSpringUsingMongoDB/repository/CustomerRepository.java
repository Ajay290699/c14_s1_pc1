package com.niit.demoSpringUsingMongoDB.repository;

import com.niit.demoSpringUsingMongoDB.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, Integer> {

    public List<Customer> findBycustomername(String customername);

    @Query("{address.city:{$in:[?0]}}")
    public List<Customer> findAllCustomerByCity(String city);
}
