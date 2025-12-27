package com.personal.chauffeur_reservation_system.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.chauffeur_reservation_system.dto.CustomerDto;
import com.personal.chauffeur_reservation_system.dto.ReservationDto;
import com.personal.chauffeur_reservation_system.exceptions.CustomerNotFoundException;
import com.personal.chauffeur_reservation_system.mapper.CustomerMapper;
import com.personal.chauffeur_reservation_system.mapper.ReservationMapper;
import com.personal.chauffeur_reservation_system.model.Customer;
import com.personal.chauffeur_reservation_system.model.Reservation;
import com.personal.chauffeur_reservation_system.service.CustomerService;
import com.personal.chauffeur_reservation_system.service.ReservationService;

@WebMvcTest(controllers = ReservationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ReservationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReservationService reservationService;

    @MockitoBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer customer;

    private CustomerDto customerDto;
    
    private Reservation reservation;

    private ReservationDto reservationDto;

    @BeforeEach
    public void init() {
        customer = Customer.builder()
            .id(1L)
            .firstName("Brandon")
            .lastName("Williams")
            .email("brandonWilliams@vsu.edu")
            .phoneNumber("404-890-4527")
            .reservations(new ArrayList<>())
            .build();
        customerDto = CustomerMapper.mapToCustomerDto(customer);

        Reservation reservation = Reservation.builder()
            .id(1L)
            .pickupAddress("217 Princeton Court")
            .destination("1400 Townpark Drive")
            .date(LocalDate.now())
            .time(LocalTime.now())
            .customer(customer)
            .build();

        reservationDto = ReservationMapper.mapReservationToReservationDto(reservation);

    }

    @Test
    public void ReservationController_CreateReservation_ReturnReservationDtoResponse() throws Exception {
        when(reservationService.createReservation(any(ReservationDto.class))).thenReturn(reservationDto);

        ResultActions response = mockMvc.perform(post("/reservations/add-reservation")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(reservationDto)));
            
        response.andExpect(MockMvcResultMatchers.status().isCreated())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void ReservationController_GetAllReservations_ReturnListOfReservationResponse() throws Exception {
        Reservation reservation2 = Reservation.builder()
            .id(1L)
            .pickupAddress("1500 North Patterson Street")
            .destination("1400 Townpark Drive")
            .date(LocalDate.now())
            .time(LocalTime.now())
            .customer(customer)
            .build();
        ReservationDto reservationDto2 = ReservationMapper.mapReservationToReservationDto(reservation2);
        List<ReservationDto> reservations = Arrays.asList(reservationDto, reservationDto2);

        when(reservationService.getReservations()).thenReturn(reservations);

        ResultActions response = mockMvc.perform(get("/reservations")
            .contentType(MediaType.APPLICATION_JSON));
        
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ReservationController_GetReservation_ReturnReservationDtoResponse() throws Exception {
        when(reservationService.getReservationById(1L)).thenReturn(reservationDto);

        ResultActions response = mockMvc.perform(get("/reservations/reservation/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(reservationDto)));
            
        response.andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void ReservationController_UpdateReservation_ReturnReservationDtoResponse() throws Exception {
        when(reservationService.updateReservation(1L, reservationDto)).thenReturn(reservationDto);

        ResultActions response = mockMvc.perform(put("/reservations/update/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(reservationDto)));
            
        response.andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void ReservationController_DeleteReservation_ReturnDeletedMessage() throws Exception {
        when(reservationService.deleteReservationById(1L)).thenReturn("Reservation has been Deleted!");

        ResultActions response = mockMvc.perform(delete("/reservations/delete/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON));
            
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    
    
}
