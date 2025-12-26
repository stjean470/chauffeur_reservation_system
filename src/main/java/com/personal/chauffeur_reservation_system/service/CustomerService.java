package com.personal.chauffeur_reservation_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.chauffeur_reservation_system.dto.CustomerDto;
import com.personal.chauffeur_reservation_system.exceptions.CustomerNotFoundException;
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

    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customers = new ArrayList<>();
        for (Customer customer : customerRepository.findAll()) {
            customers.add(CustomerMapper.mapToCustomerDto(customer));
        }
        return customers;
    }

    public CustomerDto getCustomerById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return CustomerMapper.mapToCustomerDto(customer);
    }

    public CustomerDto updateCustomer(long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerDto(savedCustomer);
    }

    public String deleteCustomerById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customerRepository.deleteById(id);
        return "Customer: " + customer.toString() + " has been deleted!";
    } 
}
