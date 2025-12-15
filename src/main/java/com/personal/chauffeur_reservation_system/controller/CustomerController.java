package com.personal.chauffeur_reservation_system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.chauffeur_reservation_system.dto.CustomerDto;
import com.personal.chauffeur_reservation_system.service.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add-customer")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        try {
            return new ResponseEntity<CustomerDto>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
        
    }
    
    
}
