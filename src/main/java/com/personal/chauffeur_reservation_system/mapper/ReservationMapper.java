package com.personal.chauffeur_reservation_system.mapper;

import com.personal.chauffeur_reservation_system.dto.ReservationDto;
import com.personal.chauffeur_reservation_system.model.Customer;
import com.personal.chauffeur_reservation_system.model.Reservation;

public class ReservationMapper {
    public static Reservation mapReservationDtoToReservation(ReservationDto reservationDto) {
        return new Reservation(
            reservationDto.getId(),
            reservationDto.getReservationType(),
            reservationDto.getCustomer(),
            reservationDto.getTripType(),
            reservationDto.getPickupAddress(),
            reservationDto.getDestination(),
            reservationDto.getDate(),
            reservationDto.getTime(),
            reservationDto.getTrip_duration(),
            reservationDto.getNumOfGuests()     
        );
    }

        public static ReservationDto mapReservationToReservationDto(Reservation reservation) {
            return new ReservationDto(
                reservation.getId(),
                reservation.getReservationType(),
                reservation.getCustomer(),
                reservation.getTripType(),
                reservation.getPickupAddress(),
                reservation.getDestination(),
                reservation.getDate(),
                reservation.getTime(),
                reservation.getTrip_duration(),
                reservation.getNumOfGuests()     
            );
        
        }
    
}
