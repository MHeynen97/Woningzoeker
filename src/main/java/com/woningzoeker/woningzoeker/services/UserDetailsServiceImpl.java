package com.woningzoeker.woningzoeker.services;

import com.woningzoeker.woningzoeker.models.Gebruiker;
import com.woningzoeker.woningzoeker.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Gebruiker user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Gebruiker niet gevonden: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getGebruikersnaam())
                .password(user.getWachtwoord())
                .roles(user.getRol())
                .build();
    }
}