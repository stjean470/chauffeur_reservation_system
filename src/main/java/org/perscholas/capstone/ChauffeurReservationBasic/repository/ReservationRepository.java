package org.perscholas.capstone.ChauffeurReservationBasic.repository;

import org.perscholas.capstone.ChauffeurReservationBasic.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
