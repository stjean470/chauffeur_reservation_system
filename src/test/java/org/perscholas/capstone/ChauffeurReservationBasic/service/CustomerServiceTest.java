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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    void testGetAllCustomers() {
        List<Customer> customerList = Arrays.asList(customer);
        when(customerRepository.findAll()).thenReturn(customerList);

        //Act
        List<Customer> result = customerService.getAllCustomers();

        //Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(customerList, result);
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

    @Test
    void testGetCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        //Act
        Customer result = customerService.getCustomerById(1L);

        //Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(customer, result);
        Assertions.assertEquals(customer.getId(), result.getId());
    }

    /*
    @Test
    void testUpdateCustomer() {
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        //Act
        customerService.saveCustomer(customer);
        Customer updatedCustomer = new Customer();
        updatedCustomer.setFirstName("Johnny");
        updatedCustomer.setLastName("Don");
        updatedCustomer.
    } */
    @Test
    void testDeleteCustomer() {
        //Arrange
        when(customerRepository.save(customer)).thenReturn(customer);

        //Act
        Customer result = customerService.saveCustomer(customer);

        customerService.deleteCustomer(1l);
        verify(customerRepository, times(1)).deleteById(1l);
    }


}
