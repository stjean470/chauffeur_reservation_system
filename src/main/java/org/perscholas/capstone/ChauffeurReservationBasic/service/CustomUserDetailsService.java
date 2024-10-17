package org.perscholas.capstone.ChauffeurReservationBasic.service;

import org.perscholas.capstone.ChauffeurReservationBasic.model.Customer;
import org.perscholas.capstone.ChauffeurReservationBasic.model.Driver;
import org.perscholas.capstone.ChauffeurReservationBasic.repository.CustomerRepository;
import org.perscholas.capstone.ChauffeurReservationBasic.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email);
        if(customer != null) {
            String role = customer.getRole().name();
            String fullRole = "ROLE_" + role;
            return new User(customer.getEmail(), customer.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(fullRole)));
        }

        Driver driver = driverRepository.findByEmail(email);
        if(driver != null) {
            String role = driver.getRole().name();
            String fullRole = "ROLE_" + role;
            return new User(driver.getEmail(), driver.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(fullRole)));
        }

        throw new UsernameNotFoundException("User not found with email " + email);
    }


}
