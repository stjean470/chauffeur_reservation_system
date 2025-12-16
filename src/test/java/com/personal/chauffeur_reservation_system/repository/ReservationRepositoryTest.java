package com.personal.chauffeur_reservation_system.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.personal.chauffeur_reservation_system.model.Customer;
import com.personal.chauffeur_reservation_system.model.Reservation;
import com.personal.chauffeur_reservation_system.model.Reservation.ReservationBuilder;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReservationRepositoryTest {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository; 

    @Test
    public void ReservationRepository_SaveReservation_ReturnSavedReservation() {
        //Arrange. Must create the customer first before we create the reservation. Reservation is dependent on the customer
        Customer customer = Customer.builder()
            .firstName("Brandon")
            .lastName("Williams")
            .email("brandonWilliams@vsu.edu")
            .phoneNumber("404-890-4527")
            .reservations(new ArrayList<>())
            .build();

        Reservation reservation = Reservation.builder()
            .pickupAddress("217 Princeton Court")
            .destination("1400 Townpark Drive")
            .date(LocalDate.now())
            .time(LocalTime.now())
            .customer(customer)
            .build();


        //Act. Add the customer to the database before the customer
        customer.getReservations().add(reservation);
        Customer savedCustomer = customerRepository.save(customer);
        Reservation savedReservation = reservationRepository.save(reservation);

        //Assert
        Assertions.assertNotNull(savedCustomer);
        Assertions.assertNotNull(savedReservation);
        Assertions.assertNotNull(savedReservation.getCustomer()); 
        Assertions.assertTrue(savedCustomer.getReservations().size() > 0);
    }
    
}
