package org.perscholas.capstone.ChauffeurReservationBasic.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Customer;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Role;
import org.perscholas.capstone.ChauffeurReservationBasic.repository.CustomerRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;


    @BeforeEach
    void setUpCustomer() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Steevenson");
        customer.setLastName("Jean");
        customer.setEmail("steevej470@gmail.com");
        customer.setPassword("Russ0mvp$");
        customer.setPhoneNumber("4045804517");
        customer.setRole(Role.CUSTOMER);
    }

    @Test
    void testSaveCustomer_and_encodePassword() {
        //Arrange
        when(passwordEncoder.encode(customer.getPassword())).thenReturn("encoded");
        when(customerRepository.save(customer)).thenReturn(customer);

        //Act
        customerService.saveCustomer(customer);

        //Assert
        assertEquals("encoded", customer.getPassword());
        assertEquals(customer, customerRepository.save(customer));
    }


}
