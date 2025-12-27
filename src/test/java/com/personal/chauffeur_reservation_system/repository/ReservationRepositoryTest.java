package com.personal.chauffeur_reservation_system.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

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

    @Test
    public void ReservationRepository_GetAllReservations_ReturnAllReservations() {
        //Arrange. Must create the customer first before we create the reservation. Reservation is dependent on the customer
        Customer customer = Customer.builder()
            .firstName("Brandon")
            .lastName("Williams")
            .email("brandonWilliams@vsu.edu")
            .phoneNumber("404-890-4527")
            .reservations(new ArrayList<>())
            .build();

        Reservation reservation1 = Reservation.builder()
            .pickupAddress("217 Princeton Court")
            .destination("1400 Townpark Drive")
            .date(LocalDate.now())
            .time(LocalTime.now())
            .customer(customer)
            .build();
            
        Reservation reservation2 = Reservation.builder()
            .pickupAddress("378 South Cobb Drive")
            .destination("1456 Powder Springs Road")
            .date(LocalDate.now())
            .time(LocalTime.now())
            .customer(customer)
            .build();

        //Act. Add the customer to the database before the customer
        customer.getReservations().add(reservation1);
        customer.getReservations().add(reservation2);
        Customer savedCustomer = customerRepository.save(customer);
        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);

        //Assert
        Assertions.assertNotNull(savedCustomer);
        Assertions.assertNotNull(reservationRepository.findAll());
        Assertions.assertTrue(savedCustomer.getReservations().size() > 0);
        Assertions.assertTrue(reservationRepository.findAll().size() > 0);
    } 

    @Test
    public void ReservationRepository_GetReservationById_ReturnReservation() {
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
        customerRepository.save(customer);
        reservationRepository.save(reservation);

        Optional<Reservation> returnedReservation = reservationRepository.findById(1L);

        Assertions.assertNotNull(returnedReservation);
    }

    @Test
    public void ReservationRepository_UpdateReservation_ReturnUpdatedReservation() {
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

        Reservation reservationToUpdate = reservationRepository.findById(savedReservation.getId()).get();
        reservationToUpdate.setPickupAddress("2650 Bentley Road SE");
        reservationToUpdate.setDestination("1200 Ernest Barrett Parkway");
        reservationToUpdate.setDate(LocalDate.now());
        reservationToUpdate.setTime(LocalTime.now());

        Reservation updatedReservation = reservationRepository.save(reservationToUpdate);

        //Assert
        Assertions.assertNotNull(savedCustomer);
        Assertions.assertNotNull(updatedReservation);
        Assertions.assertTrue(savedCustomer.getReservations().size() > 0);
    }
    
     @Test
    public void ReservationRepository_DeleteReservationById_ReturnReservation() {
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
        customerRepository.save(customer);
        reservationRepository.save(reservation);

        reservationRepository.deleteById(reservation.getId());

        Optional<Reservation> returnedReservation = reservationRepository.findById(reservation.getId());

        Assertions.assertTrue(returnedReservation.isEmpty());
    }
}
