package com.maybank.controllers;


import com.maybank.entity.Customer;
import com.maybank.model.request.CustomerRequest;
import com.maybank.model.response.CustomerResponse;
import com.maybank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public CustomerResponse createAccount(@RequestBody CustomerRequest request) {
        return customerService.createCustomer(request.getName());
    }
    @PostMapping("/get")
    public CustomerResponse getCustomer(@RequestBody CustomerRequest request) {
        return customerService.getCustomer(request.getId());
    }
}
