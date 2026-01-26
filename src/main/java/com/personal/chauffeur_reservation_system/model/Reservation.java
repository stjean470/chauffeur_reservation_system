package com.personal.chauffeur_reservation_system.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Builder
@Table(name = "RESERVATION")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reservation_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;

    @Embedded
    private Customer customer;

    @Column(name = "trip_type")
    private String tripType;
    
    @Column(name = "pickupAddress", nullable = false)
    private String pickupAddress;
    
    @Column(name = "destination")
    private String destination;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "trip_duration")
    private String trip_duration;

    @Column(name = "num_of_guests", nullable = false)
    private int numOfGuests;

    public Reservation() {}

    public Reservation(ReservationType reservationType, Customer customer, String tripType, String destination, LocalDate date,
            LocalTime time, String trip_duration, int numOfGuests) {
        this.reservationType = reservationType;
        this.customer = customer;
        this.tripType = tripType;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.trip_duration = trip_duration;
        this.numOfGuests = numOfGuests;
    }

    public Reservation(long id, ReservationType reservationType, Customer customer, String tripType, String pickupAddress, String destination,
            LocalDate date, LocalTime time, String trip_duration, int numOfGuests) {
        this.id = id;
        this.reservationType = reservationType;
        this.customer = customer;
        this.tripType = tripType;
        this.pickupAddress = pickupAddress;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.trip_duration = trip_duration;
        this.numOfGuests = numOfGuests;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getTrip_duration() {
        return trip_duration;
    }

    public void setTrip_duration(String trip_duration) {
        this.trip_duration = trip_duration;
    }

    public int getNumOfGuests() {
        return numOfGuests;
    }

    public void setNumOfGuests(int numOfGuests) {
        this.numOfGuests = numOfGuests;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((reservationType == null) ? 0 : reservationType.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((tripType == null) ? 0 : tripType.hashCode());
        result = prime * result + ((pickupAddress == null) ? 0 : pickupAddress.hashCode());
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((trip_duration == null) ? 0 : trip_duration.hashCode());
        result = prime * result + numOfGuests;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reservation other = (Reservation) obj;
        if (id != other.id)
            return false;
        if (reservationType != other.reservationType)
            return false;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (tripType == null) {
            if (other.tripType != null)
                return false;
        } else if (!tripType.equals(other.tripType))
            return false;
        if (pickupAddress == null) {
            if (other.pickupAddress != null)
                return false;
        } else if (!pickupAddress.equals(other.pickupAddress))
            return false;
        if (destination == null) {
            if (other.destination != null)
                return false;
        } else if (!destination.equals(other.destination))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (trip_duration == null) {
            if (other.trip_duration != null)
                return false;
        } else if (!trip_duration.equals(other.trip_duration))
            return false;
        if (numOfGuests != other.numOfGuests)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", reservationType=" + reservationType + ", customer=" + customer
                + ", tripType=" + tripType + ", pickupAddress=" + pickupAddress + ", destination=" + destination
                + ", date=" + date + ", time=" + time + ", trip_duration=" + trip_duration + ", numOfGuests="
                + numOfGuests + "]";
    }

    

    

    

    
    

    
}
