package com.personal.chauffeur_reservation_system.mapper;

import com.personal.chauffeur_reservation_system.dto.CustomerDto;
import com.personal.chauffeur_reservation_system.model.Customer;

public class CustomerMapper {
    public static Customer mapToCustomer(CustomerDto customerDto) {
        return new Customer(
            customerDto.getFirstName(), 
            customerDto.getLastName(), 
            customerDto.getEmail(), 
            customerDto.getPhoneNumber(), 
            null);
    }

    public static CustomerDto mapToCustomerDto (Customer customer) {
        return new CustomerDto (
            customer.getId(),
            customer.getFirstName(), 
            customer.getLastName(), 
            customer.getEmail(), 
            customer.getPhoneNumber()
        );
    }
}
