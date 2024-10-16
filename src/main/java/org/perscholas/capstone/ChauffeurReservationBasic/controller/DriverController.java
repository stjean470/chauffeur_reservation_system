package org.perscholas.capstone.ChauffeurReservationBasic.controller;

import org.perscholas.capstone.ChauffeurReservationBasic.model.Driver;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Vehicle;
import org.perscholas.capstone.ChauffeurReservationBasic.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @GetMapping
    public String getAllDriver(Model model) {
        List<Driver> drivers = driverService.getAllDrivers();
        model.addAttribute("drivers", drivers);
        return "driver-list";
    }

    @GetMapping("/new")
    public String showDriverForm(Model model) {
        Driver driver = new Driver();
        model.addAttribute("driver", driver);
        return "driver-form";
    }



    @GetMapping("/driver/{id}")
    public String getDriverById(@PathVariable Long id, Model model) {
        Driver driver = driverService.getDriverById(id);
        List<Vehicle> vehicles = driverService.getAllDriverVehicles(id);
        model.addAttribute("driver", driver);
        model.addAttribute("driverVehicles", vehicles);
        return "driver-info";
    }

    @GetMapping("/edit/{id}")
    public String showEditDriverForm(@PathVariable Long id, Model model) {
        Driver driver = driverService.getDriverById(id);
        model.addAttribute("driver", driver);
        return "driver-edit-form";
    }

    @PostMapping("/save")
    public String createDriver(@ModelAttribute("driver") Driver driver) {
        driverService.saveDriver(driver);
        return "redirect:/drivers";
    }

    @PostMapping("/update")
    public String updateDriver(@ModelAttribute("driver") Driver driver) {
        driverService.updateDriver(driver.getId(), driver);
        return "redirect:/drivers/driver/" + driver.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return "redirect:/drivers";
    }

    @GetMapping("/deleteAll")
    public String deleteAllDrivers() {
        driverService.deleteAllDrivers();
        return "redirect:/drivers";
    }



}
