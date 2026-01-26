package com.personal.chauffeur_reservation_system.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.personal.chauffeur_reservation_system.model.Customer;
import com.personal.chauffeur_reservation_system.model.ReservationType;

public class ReservationDto {
    private long id;
    private ReservationType reservationType;
    private Customer customer;
    private String tripType;
    private String pickupAddress;
    private String destination;
    private LocalDate date;
    private LocalTime time;
    private String trip_duration;
    private int numOfGuests;

    public ReservationDto() {}

    public ReservationDto(ReservationType reservationType, Customer customer, String tripType, String pickupAddress, String destination, LocalDate date,
            LocalTime time, String trip_duration, int numOfGuests) {
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

    public ReservationDto(long id, ReservationType reservationType, Customer customer, String tripType, String pickupAddress, String destination,
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
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
    public String toString() {
        return "ReservationDto [id=" + id + ", reservationType=" + reservationType + ", customer=" + customer
                + ", tripType=" + tripType + ", pickupAddress=" + pickupAddress + ", destination=" + destination
                + ", date=" + date + ", time=" + time + ", trip_duration=" + trip_duration + ", numOfGuests="
                + numOfGuests + "]";
    }

    


   

    
    
}
