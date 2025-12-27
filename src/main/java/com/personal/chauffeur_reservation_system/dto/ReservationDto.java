package com.personal.chauffeur_reservation_system.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.personal.chauffeur_reservation_system.model.ReservationType;

public class ReservationDto {
    private long id;
    private ReservationType reservationType;
    private String pickupAddress;
    private String destination;
    private LocalDate date;
    private LocalTime time;
    private int trip_duration;
    private int numOfGuests;
    private long customerId;

    public ReservationDto() {}

    public ReservationDto(ReservationType reservationType, String pickupAddress, String destination, LocalDate date,
            LocalTime time, int trip_duration, int numOfGuests, long customerId) {
        this.reservationType = reservationType;
        this.pickupAddress = pickupAddress;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.trip_duration = trip_duration;
        this.numOfGuests = numOfGuests;
        this.customerId = customerId;
    }

    public ReservationDto(long id, ReservationType reservationType, String pickupAddress, String destination,
            LocalDate date, LocalTime time, int trip_duration, int numOfGuests, long customerId) {
        this.id = id;
        this.reservationType = reservationType;
        this.pickupAddress = pickupAddress;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.trip_duration = trip_duration;
        this.numOfGuests = numOfGuests;
        this.customerId = customerId;
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

    public int getTrip_duration() {
        return trip_duration;
    }

    public void setTrip_duration(int trip_duration) {
        this.trip_duration = trip_duration;
    }

    public int getNumOfGuests() {
        return numOfGuests;
    }

    public void setNumOfGuests(int numOfGuests) {
        this.numOfGuests = numOfGuests;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "ReservationDto [id=" + id + ", reservationType=" + reservationType + ", pickupAddress=" + pickupAddress
                + ", destination=" + destination + ", date=" + date + ", time=" + time + ", trip_duration="
                + trip_duration + ", numOfGuests=" + numOfGuests + ", customerId=" + customerId + "]";
    }

    
    
}
