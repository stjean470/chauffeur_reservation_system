package org.perscholas.capstone.ChauffeurReservationBasic.controller;

import org.perscholas.capstone.ChauffeurReservationBasic.model.Customer;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Driver;
import org.perscholas.capstone.ChauffeurReservationBasic.service.CustomerService;
import org.perscholas.capstone.ChauffeurReservationBasic.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticaitionController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private DriverService driverService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/registerCustomer")
    public String showCustomerRegistrationForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "registerCustomer";
    }

    @GetMapping("/registerDriver")
    public String showDriverRegistrationForm(Model model) {
        Driver driver = new Driver();
        model.addAttribute("driver", driver);
        return "registerDriver";
    }

    @PostMapping("/customer/register")
    public String createCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/login";
    }

    @PostMapping("/driver/register")
    public String registerDriver(@ModelAttribute("driver") Driver driver) {
        driverService.saveDriver(driver);
        return "redirect:/login";
    }
}
