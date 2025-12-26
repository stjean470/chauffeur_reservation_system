package com.personal.chauffeur_reservation_system.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.chauffeur_reservation_system.dto.CustomerDto;
import com.personal.chauffeur_reservation_system.exceptions.CustomerNotFoundException;
import com.personal.chauffeur_reservation_system.mapper.CustomerMapper;
import com.personal.chauffeur_reservation_system.model.Customer;
import com.personal.chauffeur_reservation_system.service.CustomerService;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer customer;

    private CustomerDto customerDto;

    @BeforeEach
    public void init() {
        customer = Customer.builder()
            .firstName("Brandon")
            .lastName("Williams")
            .email("brandonWilliams@vsu.edu")
            .phoneNumber("404-890-4527")
            .reservations(new ArrayList<>())
            .build();
        customerDto = CustomerMapper.mapToCustomerDto(customer);

    }

    @Test
    public void CustomerController_CreateCustomer_ReturnCreatedCustomer() throws Exception {
        when(customerService.createCustomer(any(CustomerDto.class))).thenReturn(customerDto);

        ResultActions response = mockMvc.perform(post("/customers/add-customer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customerDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
            //.andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(customerDto.getFirstName())))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void CustomerController_GetAllCustomers_ReturnCustomerDtoResponse() throws Exception {
        Customer customer2 = Customer.builder()
            .firstName("William")
            .lastName("Regal")
            .email("theArchitect@vsu.edu")
            .phoneNumber("331-243-2921")
            .reservations(new ArrayList<>())
            .build();

        CustomerDto customerDto2 = CustomerMapper.mapToCustomerDto(customer2);
        List<CustomerDto> customerDtos = Arrays.asList(customerDto, customerDto2);
        when(customerService.getAllCustomers()).thenReturn(customerDtos);

        ResultActions response = mockMvc.perform(get("/customers")
            .contentType(MediaType.APPLICATION_JSON));
        
            response.andExpect(MockMvcResultMatchers.status().isOk());
                
    }

    @Test
    public void CustomerController_GetCustomer_ReturnCustomerDto() throws Exception {
        when(customerService.getCustomerById(1L)).thenReturn(customerDto);

        ResultActions response = mockMvc.perform(get("/customers/customer/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customerDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(customerDto.getFirstName())))
            .andDo(MockMvcResultHandlers.print());
    }

}
