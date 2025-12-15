package com.personal.chauffeur_reservation_system.mapper;

import com.personal.chauffeur_reservation_system.dto.ReservationDto;
import com.personal.chauffeur_reservation_system.model.Reservation;

public class ReservationMapper {
    public static Reservation mapReservationToReservation(ReservationDto reservationDto) {
        return new Reservation(
            reservationDto.getPickupAddress(),
            reservationDto.getDestination(),
            reservationDto.getDate(),
            reservationDto.getTime(),
            null
            
        );
    }

        public static ReservationDto mapReservationToReservationDto(Reservation reservation) {
            return new ReservationDto(
                reservation.getId(),
                reservation.getPickupAddress(),
                reservation.getDestination(),
                reservation.getDate(),
                reservation.getTime(),
                reservation.getCustomer().getId()
                
            );
        
        }
    
}
