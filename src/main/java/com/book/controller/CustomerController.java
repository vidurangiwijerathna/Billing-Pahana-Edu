package com.book.controller;

import org.springframework.web.bind.annotation.*;
import com.book.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    // Constructor Injection of CustomerService
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Sample Endpoint to Get Customer by ID
    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable Long id) {
        // Example: You can return customerService.getCustomerById(id) instead
        return "Customer ID: " + id;
    }

    // Sample Endpoint to test controller
    @GetMapping("/hello")
    public String helloCustomer() {
        return "Hello from Customer Controller!";
    }
}
