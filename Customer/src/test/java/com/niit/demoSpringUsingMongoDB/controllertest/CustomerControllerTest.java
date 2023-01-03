package com.niit.demoSpringUsingMongoDB.controllertest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.demoSpringUsingMongoDB.controller.CustomerController;
import com.niit.demoSpringUsingMongoDB.domain.Address;
import com.niit.demoSpringUsingMongoDB.domain.Customer;
import com.niit.demoSpringUsingMongoDB.exception.CustomerAlreadyExistException;
import com.niit.demoSpringUsingMongoDB.exception.CustomerNotFoundException;
import com.niit.demoSpringUsingMongoDB.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    CustomerServiceImpl customerService;
    @InjectMocks
    CustomerController customerController;
    @Autowired
    MockMvc mockMvc;
    Customer customer;
    Address address;

    @BeforeEach
    void setUp() {
        this.address = new Address(440012, "Mumbai");
        this.customer = new Customer(1002, "Puja", 98765678, this.address);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void givenCustomerToSaveReturnSavedCustomer() throws Exception {
        when(customerService.addCustomer(any())).thenReturn(customer);
        mockMvc.perform(post("/api/v1/customer").
                contentType(MediaType.APPLICATION_JSON).
                content(convertJsonToString(customer))).
                andExpect(status().isCreated()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenCustomerToSaveReturnSavedCustomerFailuer() throws Exception {
        when(customerService.addCustomer(any())).thenThrow(CustomerAlreadyExistException.class);
        mockMvc.perform(post("/api/v1/customer").
                        contentType(MediaType.APPLICATION_JSON).
                        content(convertJsonToString(customer))).
                    andExpect(status().isConflict()).
                     andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void givenCustomerToDeleteCustomer() throws Exception {
        String result="customer deleted succefully";
        when(customerService.deleteCustomer(anyInt())).thenReturn(result);
        mockMvc.perform(delete("/api/v1/customer/1002")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void givenCustomerToDeleteCustomerFailuer() throws Exception {
        String result="customer deleted succefully";
        when(customerService.deleteCustomer(anyInt())).thenThrow(CustomerNotFoundException.class);
        mockMvc.perform(delete("/api/v1/customer/1002")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
    }

    private static String convertJsonToString(final Object ob) {
        String result;
        //create object ObjectMapper class
        ObjectMapper mapper=new ObjectMapper();
        // call method writeValueAsString --> convert object to string
        try {
            String jsoncontent= mapper.writeValueAsString(ob);
            result=jsoncontent;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            result="Json parser error";
        }
        return result;
    }

}
