package org.perscholas.capstone.ChauffeurReservationBasic.controller;

import org.perscholas.capstone.ChauffeurReservationBasic.model.Customer;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Driver;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Vehicle;
import org.perscholas.capstone.ChauffeurReservationBasic.service.DriverService;
import org.perscholas.capstone.ChauffeurReservationBasic.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private DriverService driverService;

    @GetMapping
    public String getAllVehicles(Model model) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("Vehicles", vehicles);
        return "vehicle-list";
    }

    @GetMapping("/new/{id}")
    public String showCreateVehicleForm(@PathVariable Long id, Model model) {
        Vehicle vehicle = new Vehicle();
        Driver driver = driverService.getDriverById(id);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("driver", driver);
        return "vehicle-form";
    }

    //Mapping call to show vehicle info page
    @GetMapping("/vehicle/{id}")
    public String getVehicleById(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        Driver driver = vehicle.getDriver();
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("driver", driver);
        return "vehicle-info";
    }

    @GetMapping("/edit/{id}")
    public String showEditVehicleForm(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        return "vehicle-edit-form";
    }

    @PostMapping("/save")
    public String createVehicle(@ModelAttribute("vehicle") Vehicle vehicle) {
        vehicleService.saveVehicle(vehicle);
        Driver driver = vehicle.getDriver();
        return "redirect:/drivers/driver/" + driver.getId();
    }

    @PostMapping("/update")
    public String updateVehicle(@ModelAttribute("vehicle") Vehicle vehicle) {
        vehicleService.updateVehicle(vehicle.getId(), vehicle);
        return "redirect:/vehicles/vehicle/" + vehicle.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicles";
    }

    @GetMapping("/deleteAll")
    public String deleteAllVehicles() {
        vehicleService.deleteAllVehicles();
        return "redirect:/vehicles";
    }

}
