package com.personal.chauffeur_reservation_system.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
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
import com.personal.chauffeur_reservation_system.dto.ReservationDto;
import com.personal.chauffeur_reservation_system.mapper.CustomerMapper;
import com.personal.chauffeur_reservation_system.mapper.ReservationMapper;
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

    private Customer customer;

    private CustomerDto customerDto;

    private Reservation reservation;

    private ReservationDto reservationDto;

    @BeforeEach
    public void init() {
        customer = Customer.builder()
            .id(1L)
            .firstName("Brandon")
            .lastName("Williams")
            .email("brandonWilliams@vsu.edu")
            .phoneNumber("404-890-4527")
            .reservations(new ArrayList<>())
            .build();
        customerDto = CustomerMapper.mapToCustomerDto(customer);

        reservation = Reservation.builder()
            .id(1L)
            .pickupAddress("217 Princeton Court")
            .destination("1400 Townpark Drive")
            .date(LocalDate.now())
            .time(LocalTime.now())
            .customer(customer)
            .build();
        
        reservationDto = ReservationMapper.mapReservationToReservationDto(reservation);
    }



    @Test
    public void ReservationService_createReservation_ReturnSavedReservation() {

        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(reservation);

        CustomerDto saveCustomer = customerService.createCustomer(customerDto);
        ReservationDto savedReservation = reservationService.createReservation(reservationDto);


        Assertions.assertNotNull(saveCustomer);
        Assertions.assertNotNull(savedReservation);
    }

    @Test
    public void ReservationService_GetAllReservations_ReturnListOfReservations() {
        Reservation reservation2 = Reservation.builder()
            .id(1L)
            .pickupAddress("1500 North Patterson Street")
            .destination("1400 Townpark Drive")
            .date(LocalDate.now())
            .time(LocalTime.now())
            .customer(customer)
            .build();
        
        List<Reservation> reservations = Arrays.asList(reservation, reservation2);

        when(reservationRepository.findAll()).thenReturn(reservations);

        List<ReservationDto> reservationDtos = reservationService.getReservations();

        Assertions.assertNotNull(customer);
        Assertions.assertNotNull(reservationDtos);
        verify(reservationRepository, times(1)).findAll(); 
    }

    @Test
    public void ReservationService_GetReservationById_ReturnReservationDto() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        ReservationDto savedReservation = reservationService.getReservationById(1L);

        Assertions.assertNotNull(savedReservation);
    }

    

    @Test
    public void ReservationService_UpdateReservation_ReturnSavedReservation() {
        when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(reservation);
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        
        ReservationDto updatedReservation = reservationService.updateReservation(1L, reservationDto);

        Assertions.assertNotNull(updatedReservation);
    }
    


}
