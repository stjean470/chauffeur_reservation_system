package com.personal.chauffeur_reservation_system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.chauffeur_reservation_system.dto.CustomerDto;
import com.personal.chauffeur_reservation_system.exceptions.CustomerNotFoundException;
import com.personal.chauffeur_reservation_system.service.CustomerService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        try {
            return new ResponseEntity<List<CustomerDto>>(customerService.getAllCustomers(), HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
    
    @PostMapping("/add-customer")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        try {
            return new ResponseEntity<CustomerDto>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
        }catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
        
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable long id) {
        try {
            return new ResponseEntity<CustomerDto>(customerService.getCustomerById(id), HttpStatus.OK);
        }catch (CustomerNotFoundException cnfe) {
            return ResponseEntity.notFound().header("message", cnfe.getMessage()).build();
        }catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable long id, @RequestBody CustomerDto customerDto) {
        try {
            return new ResponseEntity<CustomerDto>(customerService.updateCustomer(id, customerDto), HttpStatus.OK);
        }catch (CustomerNotFoundException cnfe) {
            return ResponseEntity.notFound().header("message", cnfe.getMessage()).build();
        }catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
        
    }

    @DeleteMapping("/delete/{id}") 
    public ResponseEntity<String> deleteCustomer(@PathVariable long id) {
        try {
            return new ResponseEntity<String>(customerService.deleteCustomerById(id), HttpStatus.OK);
        }catch (CustomerNotFoundException cnfe) {
            return ResponseEntity.notFound().header("message", cnfe.getMessage()).build();
        }catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
    
    
    
}
