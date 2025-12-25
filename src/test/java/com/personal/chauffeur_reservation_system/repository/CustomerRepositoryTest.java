package com.personal.chauffeur_reservation_system.repository;


import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.personal.chauffeur_reservation_system.model.Customer;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerRepositoryTest {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void CustomerRepository_SaveAll_ReturnSavedCustomer() {
        //Arrange
        Customer customer = Customer.builder()
            .firstName("Brandon")
            .lastName("Williams")
            .email("brandonWilliams@vsu.edu")
            .phoneNumber("404-890-4527")
            .reservations(null)
            .build();

        //Act
        Customer savedCustomer = customerRepository.save(customer);

        //Assert
        Assertions.assertNotNull(savedCustomer);
        Assertions.assertTrue(savedCustomer.getId() > 0);
    }

    @Test
    public void CustomerRepository_GetAll_GetListOfCustomers() {
        //Arrange
        Customer customer1 = Customer.builder()
            .firstName("Randy")
            .lastName("Orton")
            .email("theviper@wwe.com")
            .phoneNumber("838-090-2127")
            .reservations(null)
            .build();
        
        Customer customer2 = Customer.builder()
            .firstName("D'vorah")
            .lastName("Beetle")
            .email("thehive@mortalkombat.com")
            .phoneNumber("331-365-2847")
            .reservations(null)
            .build();

        //Act
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        //Assert
        Assertions.assertNotNull(customerRepository.findAll());
        Assertions.assertTrue(customerRepository.findAll().size() > 0);
    }

    @Test 
    public void CustomerRepository_GetCustomerById_ReturnCustomer() {
         Customer customer = Customer.builder()
            .firstName("Brandon")
            .lastName("Williams")
            .email("brandonWilliams@vsu.edu")
            .phoneNumber("404-890-4527")
            .reservations(null)
            .build();

        //Act
        customerRepository.save(customer);

        Optional<Customer> returnedCustomer = customerRepository.findById(1L);

        Assertions.assertNotNull(returnedCustomer);
        

    }
}
