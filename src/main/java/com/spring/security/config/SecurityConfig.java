package com.spring.security.config;

import com.spring.security.entity.PermissionsAllowed;
import com.spring.security.entity.Role;
import com.spring.security.filters.JwtAuthFilter;
import com.spring.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home/health", "/h2-console").permitAll()
                        .requestMatchers("/authenticate").permitAll()
                      //  .requestMatchers("/home/userDetails").hasAnyRole(Role.USER.name())
//                        .requestMatchers(HttpMethod.GET, "/home/**").hasAuthority(PermissionsAllowed.USER_READ.name())
//                        .requestMatchers(HttpMethod.POST, "/home/**").hasAuthority(PermissionsAllowed.USER_WRITE.name())
//                        .requestMatchers(HttpMethod.DELETE,"/home/**").hasAuthority(PermissionsAllowed.USER_DELETE.name())
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(final UserDetailsService userDetailsService,
                                                       final PasswordEncoder passwordEncoder) {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }

}
