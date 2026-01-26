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
import com.personal.chauffeur_reservation_system.repository.ReservationRepository;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationDto createReservation(ReservationDto reservationDto) {
        
        Reservation reservation = ReservationMapper.mapReservationDtoToReservation(reservationDto);
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
        reservation.setReservationType(reservationDto.getReservationType());
        reservation.setPickupAddress(reservationDto.getPickupAddress());
        reservation.setCustomer(reservationDto.getCustomer());
        reservation.setTripType(reservationDto.getTripType());
        reservation.setDestination(reservationDto.getDestination());
        reservation.setDate(reservationDto.getDate());
        reservation.setTime(reservationDto.getTime());
        reservation.setTrip_duration(reservationDto.getTrip_duration());
        reservation.setNumOfGuests(reservationDto.getNumOfGuests());
        Reservation updatedReservation = reservationRepository.save(reservation);
        return ReservationMapper.mapReservationToReservationDto(updatedReservation);
    }

    public String deleteReservationById(long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
        reservationRepository.deleteById(id);
        return reservation.toString() + " has been deleted!";
    }

}
