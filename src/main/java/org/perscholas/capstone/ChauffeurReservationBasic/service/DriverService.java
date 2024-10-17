package org.perscholas.capstone.ChauffeurReservationBasic.service;

import org.perscholas.capstone.ChauffeurReservationBasic.model.Customer;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Driver;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Vehicle;
import org.perscholas.capstone.ChauffeurReservationBasic.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public List<Vehicle> getAllDriverVehicles(Long id) {
        return driverRepository.findById(id)
                .map(Driver::getVehicles)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
    }

    public Driver findByEmail(String email) {
        return driverRepository.findByEmail(email);
    }

    public Driver saveDriver(Driver driver) {
        driver.setPassword(passwordEncoder.encode(driver.getPassword()));
        return driverRepository.save(driver);
    }

    public Driver updateDriver(Long id, Driver updatedDriver) {
        return driverRepository.findById(id)
                .map(driver -> {
                    driver.setFirstName(updatedDriver.getFirstName());
                    driver.setLastName(updatedDriver.getLastName());
                    driver.setEmail(updatedDriver.getEmail());
                    driver.setPassword(passwordEncoder.encode(updatedDriver.getPassword()));
                    driver.setPhoneNumber(updatedDriver.getPhoneNumber());
                    return driverRepository.save(driver);
                })
                .orElseThrow(() -> new RuntimeException("Driver was not found"));
    }



    public Driver updateDriverVehicles(Long id, Vehicle vehicle) {
        return driverRepository.findById(id)
                .map(driver -> {
                    List<Vehicle> vehicles = driver.getVehicles();
                    vehicles.add(vehicle);
                    driver.setVehicles(vehicles);
                    return driverRepository.save(driver);
                })
                .orElseThrow(() -> new RuntimeException("Driver not found"));
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    public void deleteAllDrivers() {
        driverRepository.deleteAll();
    }
}
