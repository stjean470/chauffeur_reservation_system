package org.perscholas.capstone.ChauffeurReservationBasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/services")
    public String servicePage() {
        return "services";
    }
}
