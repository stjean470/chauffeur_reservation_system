package com.personal.chauffeur_reservation_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.chauffeur_reservation_system.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{ 
  
}
