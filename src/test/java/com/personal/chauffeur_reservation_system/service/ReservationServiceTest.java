package com.personal.chauffeur_reservation_system.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

import com.personal.chauffeur_reservation_system.dto.CustomerDto;
import com.personal.chauffeur_reservation_system.dto.ReservationDto;
import com.personal.chauffeur_reservation_system.model.Customer;
import com.personal.chauffeur_reservation_system.model.Reservation;
import com.personal.chauffeur_reservation_system.repository.CustomerRepository;
import com.personal.chauffeur_reservation_system.repository.ReservationRepository;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private ReservationService reservationService;


    @InjectMocks
    private CustomerService customerService;


    @Test
    public void ReservationService_createReservation_ReturnSavedReservation() {
        //Arrange creating the customer before the Reservation
        Customer customer = Customer.builder()
            .id(1L)
            .firstName("Brandon")
            .lastName("Williams")
            .email("brandonWilliams@vsu.edu")
            .phoneNumber("404-890-4527")
            .reservations(new ArrayList<>())
            .build();

        CustomerDto customerDto = CustomerDto.builder()
            .firstName("Brandon")
            .lastName("Williams")
            .email("brandonWilliams@vsu.edu")
            .phoneNumber("404-890-4527")
            .build();

        Reservation reservation = Reservation.builder()
            .id(1L)
            .pickupAddress("217 Princeton Court")
            .destination("1400 Townpark Drive")
            .date(LocalDate.now())
            .time(LocalTime.now())
            .customer(customer)
            .build();
        
        ReservationDto reservationDto = ReservationDto.builder()
            .pickupAddress("217 Princeton Court")
            .destination("1400 Townpark Drive")
            .date(LocalDate.now())
            .time(LocalTime.now())
            .customerId(customer.getId())
            .build();
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(reservation);

        CustomerDto saveCustomer = customerService.createCustomer(customerDto);
        ReservationDto savedReservation = reservationService.createReservation(reservationDto);

        Assertions.assertNotNull(saveCustomer);
        Assertions.assertNotNull(savedReservation);


    }
}
