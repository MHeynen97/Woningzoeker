package com.woningzoeker.woningzoeker.repositories;

import com.woningzoeker.woningzoeker.models.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GebruikerRepository extends JpaRepository<Gebruiker, Long> {
    List<Gebruiker> findByGebruikersnaam(String gebruikersnaam);
}
