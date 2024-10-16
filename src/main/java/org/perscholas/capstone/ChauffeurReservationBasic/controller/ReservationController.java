package org.perscholas.capstone.ChauffeurReservationBasic.controller;

import org.perscholas.capstone.ChauffeurReservationBasic.model.Customer;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Reservation;
import org.perscholas.capstone.ChauffeurReservationBasic.service.CustomerService;
import org.perscholas.capstone.ChauffeurReservationBasic.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String getAllReservations(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("Reservations", reservations);
        return "reservations-list";
    }

    @GetMapping("/new/{id}")
    public String showCreateReservation(@PathVariable Long id, Model model) {
        Reservation reservation = new Reservation();
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("reservation", reservation);
        model.addAttribute("customer", customer);
        return "reservation-form";
    }

    @GetMapping("/reservation/{id}")
    public String getReservationById(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.getReservationById(id);
        Customer customer = reservation.getCustomer();
        model.addAttribute("reservation", reservation);
        model.addAttribute("customer", customer);
        return "reservation-info";
    }

    @GetMapping("/edit/{id}")
    public String showEditReservationForm(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.getReservationById(id);
        model.addAttribute("reservation", reservation);
        return "reservation-edit-form";
    }

    @PostMapping("/save")
    public String createReservation(@ModelAttribute("reservation") Reservation reservation) {
        reservationService.saveReservation(reservation);
        Customer customer = reservation.getCustomer();
        return "redirect:/customers/customer/" + customer.getId();
    }

    @PostMapping("/update")
    public String updateReservation(@ModelAttribute("reservation") Reservation reservation) {
        reservationService.updateReservation(reservation.getId(), reservation);
        return "redirect:/reservations/reservation/" + reservation.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return "redirect:/reservations";
    }

    @GetMapping("/deleteAll")
    public String deleteAllReservations() {
        reservationService.deleteAllReservations();
        return "redirect:/reservations";
    }
}
