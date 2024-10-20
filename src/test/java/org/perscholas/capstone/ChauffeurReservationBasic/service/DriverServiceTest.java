package org.perscholas.capstone.ChauffeurReservationBasic.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Driver;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Role;
import org.perscholas.capstone.ChauffeurReservationBasic.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class DriverServiceTest {
    @Mock
    private DriverRepository driverRepository;

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
    void testSaveDriver() {

    }
}
