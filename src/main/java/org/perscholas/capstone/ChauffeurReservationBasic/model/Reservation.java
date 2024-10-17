package org.perscholas.capstone.ChauffeurReservationBasic.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pickupAddress")
    private String pickupAddress;

    @Column(name = "dropoffAddress")
    private String dropoffAddress;

    @Column(name = "reservationDate")
    private LocalDate date;

    @Column(name = "reservationTime")
    private LocalTime time;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
