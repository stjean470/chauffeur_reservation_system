package com.personal.chauffeur_reservation_system.service;

import org.springframework.stereotype.Service;

import com.personal.chauffeur_reservation_system.repository.ReservationRepository;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

}
