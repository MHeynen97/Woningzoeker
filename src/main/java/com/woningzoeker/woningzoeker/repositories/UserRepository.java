package com.woningzoeker.woningzoeker.repositories;

import com.woningzoeker.woningzoeker.models.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Gebruiker, Long> {
    Optional<Gebruiker> findByGebruikersnaam(String gebruikersnaam);
}
