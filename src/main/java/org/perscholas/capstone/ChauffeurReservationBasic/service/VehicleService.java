package org.perscholas.capstone.ChauffeurReservationBasic.service;

import org.perscholas.capstone.ChauffeurReservationBasic.model.Vehicle;
import org.perscholas.capstone.ChauffeurReservationBasic.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicle.setYear(updatedVehicle.getYear());
                    vehicle.setBrand(updatedVehicle.getBrand());
                    vehicle.setModel(updatedVehicle.getModel());
                    vehicle.setTrim(updatedVehicle.getTrim());
                    vehicle.setPassengerCapacity(updatedVehicle.getPassengerCapacity());
                    return vehicleRepository.save(vehicle);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public void deleteAllVehicles() {
        vehicleRepository.deleteAll();
    }
}
