package com.niit.demoSpringUsingMongoDB.servicetest;


import com.niit.demoSpringUsingMongoDB.domain.Address;
import com.niit.demoSpringUsingMongoDB.domain.Customer;
import com.niit.demoSpringUsingMongoDB.exception.CustomerAlreadyExistException;
import com.niit.demoSpringUsingMongoDB.exception.CustomerNotFoundException;
import com.niit.demoSpringUsingMongoDB.repository.CustomerRepository;
import com.niit.demoSpringUsingMongoDB.service.CustomerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceimplTest {

    @Mock
    CustomerRepository customerRepository;
    @InjectMocks
    CustomerServiceImpl customerService;
    private Customer customer;
    private Address address;
    @BeforeEach
    public void setUp(){
        this.address=new Address(440013,"Bhopal");
        this.customer=new Customer(10003,"Satyam",987654329,this.address);
    }
    @AfterEach
    public void clear(){
        this.address=null;
        this.customer=null;
    }
    @Test
    public void givenCustomerToSaveShouldReturnCustomerSuccess() throws CustomerAlreadyExistException {
            //create mock for repo methods
        when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.ofNullable(null));
        when(customerRepository.save(customer)).thenReturn(customer);
        assertEquals(customer,customerService.addCustomer(customer));
        verify(customerRepository,times(1)).save(customer);
    }
    @Test
    public void givenCustomerToSaveShouldReturnCustomerFailuer(){
        when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.ofNullable(customer));
        assertThrows(CustomerAlreadyExistException.class,()->customerService.addCustomer(customer));
    }
    @Test
    public void givenCustomerToDeleteShouldDeleteCustomerSuccess() throws CustomerNotFoundException {
        when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.ofNullable(customer));
        String result=customerService.deleteCustomer(customer.getCustomerId());
        assertEquals("Customer Deleted succefully",result);
    }
    @Test
    public void givenCustomerToDeleteShouldDeleteCustomerFailuer(){
        when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.ofNullable(null));
        assertThrows(CustomerNotFoundException.class,()->customerService.deleteCustomer(customer.getCustomerId()));
    }


}
