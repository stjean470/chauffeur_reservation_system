package org.perscholas.capstone.ChauffeurReservationBasic.config;

import org.perscholas.capstone.ChauffeurReservationBasic.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
                        .requestMatchers("/registerCustomer", "/registerDriver", "/customer/register", "/driver/register", "/login", "/css/**", "/imgs/**").permitAll()
                        .requestMatchers("/drivers/**", "/drivers", "/vehicles/**").hasRole("DRIVER")
                        .requestMatchers("/customers/**", "/home", "/customers", "/reservations/**").hasAnyRole("CUSTOMER", "DRIVER")
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            authentication.getAuthorities().forEach(grantedAuthority -> {
                                String role = grantedAuthority.getAuthority();
                                try {
                                    if (role.equals("ROLE_DRIVER")) {
                                        response.sendRedirect("/drivers");
                                    }else if(role.equals("ROLE_CUSTOMER")) {
                                        response.sendRedirect("/customers");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        }))
                .logout(logout -> logout.logoutSuccessUrl("/login").permitAll())
                .userDetailsService(userDetailsService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
