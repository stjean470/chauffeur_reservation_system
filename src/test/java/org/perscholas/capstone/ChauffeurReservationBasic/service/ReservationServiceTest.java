package org.perscholas.capstone.ChauffeurReservationBasic.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Customer;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Reservation;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Role;
import org.perscholas.capstone.ChauffeurReservationBasic.repository.ReservationRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
public class ReservationServiceTest {


    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ReservationService reservationService;

    private Customer customer;
    private Reservation reservation;


    @BeforeEach
    void setReservationUp() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Steevenson");
        customer.setLastName("Jean");
        customer.setEmail("steevej470@gmail.com");
        customer.setPassword("Russ0mvp$");
        customer.setPhoneNumber("4045804517");
        customer.setRole(Role.CUSTOMER);

        reservation = new Reservation();
        reservation.setId(1);
        reservation.setPickupAddress("217 Princeton Court");
        reservation.setDropoffAddress("946 Battery Avenue");
        reservation.setDate(LocalDate.now());
        reservation.setTime(LocalTime.NOON);
        reservation.setCustomer(customer);

    }

    @Test
    void testReturnAllTasks() {
        //Arrange
        List<Reservation> reservations = Arrays.asList(reservation);
        when(reservationRepository.findAll()).thenReturn(reservations);
        //Act
        List<Reservation> resultList = reservationService.getAllReservations();
        //Assert
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(reservations.size(), resultList.size());
    }

    @Test
    void testAddReservation_To_Customer() {
        //Arrange
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        //Act
        Reservation reserve = reservationService.saveReservation(reservation);

        //Assert
        Assertions.assertNotNull(reserve);
        Assertions.assertEquals(reservation.getId(), reserve.getId());
    }

    @Test
    void testGetReservationById() {
        //Arrange
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        //Act
        Reservation identifiedReservation = reservationService.getReservationById(1L);

        //Assert
        Assertions.assertNotNull(identifiedReservation);
        Assertions.assertEquals(reservation.getId(), identifiedReservation.getId());

    }

    @Test
    void testDeleteReservationById() {
        //Arrange
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        //Act
        Reservation reserve = reservationService.saveReservation(reservation);

        reservationService.deleteReservation(reserve.getId());

        verify(reservationRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateReservation() {
        //Arrange
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        //Act
        reservationService.saveReservation(reservation);
        Reservation reserve = new Reservation();
        reserve.setPickupAddress("378 Bells Ferry Road");
        reserve.setDropoffAddress("946 Shady Bell Avenue");
        reserve.setDate(LocalDate.now());
        reserve.setTime(LocalTime.now());
        reserve.setCustomer(customer);
        reservationService.updateReservation(1L, reserve);

        //Assert
        Assertions.assertEquals(reserve.getPickupAddress(), reservation.getPickupAddress());



    }
}
