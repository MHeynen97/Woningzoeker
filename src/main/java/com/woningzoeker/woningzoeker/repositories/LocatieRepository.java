package com.woningzoeker.woningzoeker.repositories;

import com.woningzoeker.woningzoeker.models.Locatie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocatieRepository extends JpaRepository<Locatie, Integer> {
    List<Locatie> findByWoonplaats(String Woonplaats);
}
