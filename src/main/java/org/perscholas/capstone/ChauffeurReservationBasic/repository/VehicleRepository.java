package org.perscholas.capstone.ChauffeurReservationBasic.repository;

import org.perscholas.capstone.ChauffeurReservationBasic.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
