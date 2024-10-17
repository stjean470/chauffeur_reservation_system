package org.perscholas.capstone.ChauffeurReservationBasic.repository;

import org.perscholas.capstone.ChauffeurReservationBasic.model.Customer;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}
