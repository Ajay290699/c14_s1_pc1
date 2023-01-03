package com.niit.demoSpringUsingMongoDB.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason = "Customer not found")
public class CustomerNotFoundException extends Exception{
}
