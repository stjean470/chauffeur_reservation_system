package com.personal.chauffeur_reservation_system.service;

import org.springframework.stereotype.Service;

import com.personal.chauffeur_reservation_system.dto.ReservationDto;
import com.personal.chauffeur_reservation_system.exceptions.CustomerNotFoundException;
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
        Reservation reservation = reservationRepository.save(ReservationMapper.mapReservationDtoToReservation(reservationDto, customerMakingReservation));
        return ReservationMapper.mapReservationToReservationDto(reservation);
    }

}
