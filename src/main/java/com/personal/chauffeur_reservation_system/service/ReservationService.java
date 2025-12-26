package com.personal.chauffeur_reservation_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.chauffeur_reservation_system.dto.ReservationDto;
import com.personal.chauffeur_reservation_system.exceptions.CustomerNotFoundException;
import com.personal.chauffeur_reservation_system.exceptions.ReservationNotFoundException;
import com.personal.chauffeur_reservation_system.mapper.ReservationMapper;
import com.personal.chauffeur_reservation_system.model.Customer;
import com.personal.chauffeur_reservation_system.model.Reservation;
import com.personal.chauffeur_reservation_system.repository.CustomerRepository;
import com.personal.chauffeur_reservation_system.repository.ReservationRepository;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;

    public ReservationService(ReservationRepository reservationRepository, CustomerRepository customerRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
    }

    public ReservationDto createReservation(ReservationDto reservationDto) {
        Customer customerMakingReservation = customerRepository.findById(reservationDto.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException("Customer does not exist. Can't make Reservation"));
        Reservation reservation = ReservationMapper.mapReservationDtoToReservation(reservationDto, customerMakingReservation);
        customerMakingReservation.getReservations().add(reservation);
        customerRepository.save(customerMakingReservation);
        Reservation savedReservation = reservationRepository.save(reservation);

        return ReservationMapper.mapReservationToReservationDto(savedReservation);
    }

    public List<ReservationDto> getReservations() {
        List<ReservationDto> reservations = new ArrayList<>();
        for (Reservation reservation : reservationRepository.findAll()) {
            reservations.add(ReservationMapper.mapReservationToReservationDto(reservation));
        }
        return reservations;
    }

    public ReservationDto getReservationById(long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
        return ReservationMapper.mapReservationToReservationDto(reservation);
    }

    public ReservationDto updateReservation(long id, ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
        reservation.setPickupAddress(reservationDto.getPickupAddress());
        reservation.setDestination(reservationDto.getDestination());
        reservation.setDate(reservationDto.getDate());
        reservation.setTime(reservationDto.getTime());
        return ReservationMapper.mapReservationToReservationDto(reservation);
    }

}
