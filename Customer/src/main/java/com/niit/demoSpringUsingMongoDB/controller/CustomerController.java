package com.niit.demoSpringUsingMongoDB.controller;

import com.niit.demoSpringUsingMongoDB.domain.Customer;
import com.niit.demoSpringUsingMongoDB.exception.CustomerAlreadyExistException;
import com.niit.demoSpringUsingMongoDB.exception.CustomerNotFoundException;
import com.niit.demoSpringUsingMongoDB.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {

    ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
        try {
            return new ResponseEntity<>(customerService.addCustomer(customer),HttpStatus.CREATED);
        } catch (CustomerAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/customer")
    public ResponseEntity<?> getAllCustomer(){
        return new ResponseEntity<>(customerService.getAllCustomer(),HttpStatus.CREATED);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable int id){
        try {
            return new ResponseEntity<>(customerService.deleteCustomer(id),HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/customers/{name}")
    public ResponseEntity<?> getAllCustomerByName(@PathVariable String name){
        return new ResponseEntity<>(customerService.getCustomerByName(name),HttpStatus.CREATED);
    }

    @GetMapping("/customer/{city}")
    public ResponseEntity<?> getAllCustomerByCity(@PathVariable String city){
        return new ResponseEntity<>(customerService.getCustomerByCity(city),HttpStatus.CREATED);
    }


}
