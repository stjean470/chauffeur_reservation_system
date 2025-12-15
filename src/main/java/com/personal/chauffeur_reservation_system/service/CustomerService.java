package com.personal.chauffeur_reservation_system.service;

import org.springframework.stereotype.Service;

import com.personal.chauffeur_reservation_system.dto.CustomerDto;
import com.personal.chauffeur_reservation_system.mapper.CustomerMapper;
import com.personal.chauffeur_reservation_system.model.Customer;
import com.personal.chauffeur_reservation_system.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.save(CustomerMapper.mapToCustomer(customerDto));
        return CustomerMapper.mapToCustomerDto(customer); 
    } 
}
