package com.woningzoeker.woningzoeker.config;

import com.woningzoeker.woningzoeker.security.JwtRequestFilter;
import com.woningzoeker.woningzoeker.security.JwtService;
import com.woningzoeker.woningzoeker.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService) throws Exception {

        http
                .httpBasic(hp -> hp.disable()) //aanpassing
                .authorizeHttpRequests(auth -> auth
                        // Publieke endpoints
                        // Open endpoints
                        .requestMatchers("/api-docs/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/huis/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/locatie/**").permitAll()

                        // Gebruikers en Profielen en ContactInfo GET: toegankelijk voor USER of ADMIN
                        //.requestMatchers(HttpMethod.GET, "/profielen").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/gebruiker/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/profiel/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/contactinfo/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/bieding/**").hasAnyRole("USER", "ADMIN")

                        // Alleen gebruikers mogen zelf bieden
                        .requestMatchers(HttpMethod.POST, "/biedingen/**").hasAnyRole("USER", "ADMIN")

                        // Alles wat aanpassen of verwijderen is (PUT/DELETE) is alleen voor Admins
                        .requestMatchers("/gebruiker/**").hasRole("ADMIN")
                        .requestMatchers("/huis/**").hasRole("ADMIN")
                        .requestMatchers("/contactinfo/**").hasRole("ADMIN")
                        .requestMatchers("/locatie/**").hasRole("ADMIN")
                        .requestMatchers("/profiel/**").hasRole("ADMIN")
                        .requestMatchers("/bieding/**").hasRole("ADMIN")

                        // Andere verzoeken worden geweigerd
                        .anyRequest().denyAll()
                )
                .addFilterBefore(new JwtRequestFilter(jwtService), UsernamePasswordAuthenticationFilter.class) // aanpassing
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

        return builder.build();
    }
}