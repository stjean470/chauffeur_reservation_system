package com.personal.chauffeur_reservation_system.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.personal.chauffeur_reservation_system.dto.CustomerDto;
import com.personal.chauffeur_reservation_system.mapper.CustomerMapper;
import com.personal.chauffeur_reservation_system.model.Customer;
import com.personal.chauffeur_reservation_system.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService; 

    private Customer customer;

    private CustomerDto customerDto;

    @BeforeEach
    public void init() {
        customer = Customer.builder()
            .firstName("Brandon")
            .lastName("Williams")
            .email("brandonWilliams@vsu.edu")
            .phoneNumber("404-890-4527")
            .reservations(new ArrayList<>())
            .build();
        customerDto = CustomerMapper.mapToCustomerDto(customer);
    }

    @Test
    public void CustomerService_CreateCustomer_ReturnCustomerDto() {

        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

        CustomerDto saveCustomer = customerService.createCustomer(customerDto);

        Assertions.assertNotNull(saveCustomer);
    }

    @Test
    public void CustomerService_GetAllCustomers_ReturnListCustomerDto() {

        Customer customer2 = Customer.builder()
            .firstName("William")
            .lastName("Regal")
            .email("theArchitect@vsu.edu")
            .phoneNumber("331-243-2921")
            .reservations(new ArrayList<>())
            .build();


        List<Customer> customers = Arrays.asList(customer, customer2);
        
        
        when(customerRepository.findAll()).thenReturn(customers);


        List<CustomerDto> savedCustomers = customerService.getAllCustomers();

        Assertions.assertNotNull(savedCustomers);
        Assertions.assertEquals(2, savedCustomers.size());

        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void CustomerService_GetCustomerById_ReturnCustomerDto() {
        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));

        CustomerDto saveCustomer = customerService.getCustomerById(1L);

        Assertions.assertNotNull(saveCustomer);
    }

    @Test
    public void CustomerService_UpdateCustomer_ReturnCustomerDto() {

        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));

        CustomerDto saveCustomer = customerService.updateCustomer(1L, customerDto);

        Assertions.assertNotNull(saveCustomer);
    }

    
}
