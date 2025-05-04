package com.woningzoeker.woningzoeker.repositories;

import com.woningzoeker.woningzoeker.models.Profiel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfielRepository extends JpaRepository<Profiel, Long> {
    List<Profiel> findByGebruiker_Gebruikersnaam(String gebruikersnaam);
}
