package com.personal.chauffeur_reservation_system.repository;


import org.junit.jupiter.api.Assertions;
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
}
