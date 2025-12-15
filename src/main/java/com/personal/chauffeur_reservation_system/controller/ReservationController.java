package com.personal.chauffeur_reservation_system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.chauffeur_reservation_system.dto.ReservationDto;
import com.personal.chauffeur_reservation_system.exceptions.CustomerNotFoundException;
import com.personal.chauffeur_reservation_system.service.ReservationService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/reservations")
public class ReservationController {
    
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getReservations() {
        try {
            return new ResponseEntity<List<ReservationDto>>(reservationService.getReservations(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
    

    @PostMapping("/add-reservation")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
        try {
            return new ResponseEntity<ReservationDto>(reservationService.createReservation(reservationDto), HttpStatus.CREATED);
        } catch (CustomerNotFoundException cnfe) {
            return ResponseEntity.notFound().header("message", cnfe.getMessage()).build();
        }catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
    
    
}
