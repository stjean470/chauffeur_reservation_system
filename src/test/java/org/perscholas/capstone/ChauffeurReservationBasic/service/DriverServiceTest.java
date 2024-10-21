package org.perscholas.capstone.ChauffeurReservationBasic.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Driver;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Role;
import org.perscholas.capstone.ChauffeurReservationBasic.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
public class DriverServiceTest {
    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverService driverService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private Driver driver;

    @BeforeEach
    void setUpDriver() {
        driver = new Driver();
        driver.setId(1);
        driver.setFirstName("Maxy");
        driver.setLastName("Jean");
        driver.setEmail("maxyjn4030@gmail.com");
        driver.setPassword("Jean4030");
        driver.setPhoneNumber("6784993993");
        driver.setRole(Role.DRIVER);
    }

    @Test
    void testGetAllDrivers() {
        //Arrange
        List<Driver> driverList = Arrays.asList(driver);
        when(driverRepository.findAll()).thenReturn(driverList);

        //Act
        List<Driver> resultList = driverService.getAllDrivers();

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(driverList, resultList);
    }

    @Test
    void testSaveDriver() {
        //Arrange
        when(passwordEncoder.encode(driver.getPassword())).thenReturn("encoded");
        when(driverRepository.save(driver)).thenReturn(driver);

        //Act
        driverService.saveDriver(driver);

        //Assert
        Assertions.assertEquals("encoded", driver.getPassword());
        Assertions.assertNotNull(driver);
        Assertions.assertEquals(driver, driverRepository.save(driver));
    }

    @Test
    void testGetDriverById() {
        //Arrange
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        //Act
        Driver retrievedDriver = driverService.getDriverById(1L);

        //Assert
        Assertions.assertNotNull(retrievedDriver);
        Assertions.assertEquals(driver, retrievedDriver);

    }

    @Test
    void testUpdateDriver() {
        //Arrange
        when(driverRepository.save(driver)).thenReturn(driver);
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        //Act
        Driver updatedDriver = new Driver();
        updatedDriver.setFirstName("Jerry");
        updatedDriver.setLastName("Stackhouse");
        updatedDriver.setEmail("stackthat@yahoo.com");
        updatedDriver.setPassword("stackSome");
        updatedDriver.setPhoneNumber("8788981231");

        driverService.updateDriver(1L, updatedDriver);

        Assertions.assertEquals(updatedDriver.getFirstName(), driver.getFirstName());
    }

    @Test
    void testDeleteDriver() {
        //Arrange
        when(driverRepository.save(driver)).thenReturn(driver);

        //Act
        Driver person = driverService.saveDriver(driver);
        driverService.deleteDriver(person.getId());

        verify(driverRepository, times(1)).deleteById(1L);



    }
}


