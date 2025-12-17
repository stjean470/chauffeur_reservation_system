package com.personal.chauffeur_reservation_system.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Builder;

@Builder
public class ReservationDto {
    private long id;
    private String pickupAddress;
    private String destination;
    private LocalDate date;
    private LocalTime time;
    private long customerId;

    public ReservationDto() {}

    public ReservationDto(String pickupAddress, String destination, LocalDate date, LocalTime time, long customerId) {
        this.pickupAddress = pickupAddress;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.customerId = customerId;
    }

    public ReservationDto(long id, String pickupAddress, String destination, LocalDate date, LocalTime time,
            long customerId) {
        this.id = id;
        this.pickupAddress = pickupAddress;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.customerId = customerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "ReservationDto [id=" + id + ", pickupAddress=" + pickupAddress + ", destination=" + destination +
               ", date=" + date + ", time=" + time + ", customerId=" + customerId + "]";
    }
}
