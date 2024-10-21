package org.perscholas.capstone.ChauffeurReservationBasic.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Driver;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Role;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Vehicle;
import org.perscholas.capstone.ChauffeurReservationBasic.repository.DriverRepository;
import org.perscholas.capstone.ChauffeurReservationBasic.repository.VehicleRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
public class VehicleServiceTest {
    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @InjectMocks
    private DriverService driverService;

    private Driver driver;
    private Vehicle vehicle;
    @BeforeEach
    void setupVehicle() {
        driver = new Driver();
        driver.setId(1);
        driver.setFirstName("Maxy");
        driver.setLastName("Jean");
        driver.setEmail("maxyjn4030@gmail.com");
        driver.setPassword("Jean4030");
        driver.setPhoneNumber("6784993993");
        driver.setRole(Role.DRIVER);

        vehicle = new Vehicle();
        vehicle.setId(1);
        vehicle.setYear(2021);
        vehicle.setBrand("Lincoln");
        vehicle.setModel("Navigator");
        vehicle.setTrim("LX");
        vehicle.setPassengerCapacity((byte) 8);
        vehicle.setDriver(driver);
    }

    @Test
    void testGetAllVehicles() {
        //Arrange
        List<Vehicle> vehicles = Arrays.asList(vehicle);
        when(vehicleRepository.findAll()).thenReturn(vehicles);

        //Act
        List<Vehicle> resultList = vehicleService.getAllVehicles();

        //Assert
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(vehicles, resultList);


    }

    @Test
    void testAddVehicle() {
        //Arrange
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        //Act
        Vehicle vh = vehicleService.saveVehicle(vehicle);

        Assertions.assertEquals(vehicle.getId(), vh.getId());
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    void testGetVehicelById() {
        //Arrange
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        //Act
        Vehicle resultVehicle = vehicleService.getVehicleById(1L);

        //Assert
        Assertions.assertNotNull(resultVehicle);
        Assertions.assertEquals(vehicle.getId(), resultVehicle.getId());


    }

    @Test
    void testUpdateVehicle() {
        //Arrange
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        //Act
        Vehicle vh = vehicleService.saveVehicle(vehicle);
        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setYear(2024);
        updatedVehicle.setBrand("GMC");
        updatedVehicle.setModel("Yukon XL");
        updatedVehicle.setTrim("Denali");
        updatedVehicle.setPassengerCapacity((byte) 8);
        vehicleService.updateVehicle(1L, updatedVehicle);

        //Assert
        Assertions.assertEquals(updatedVehicle.getModel(), vh.getModel());
    }

    @Test
    void testDeleteVehicle() {
        //Arrange
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        //Act
        Vehicle vh = vehicleService.saveVehicle(vehicle);

        vehicleService.deleteVehicle(vh.getId());

        verify(vehicleRepository, times(1)).deleteById(1L);
    }
}
