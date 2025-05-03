package com.woningzoeker.woningzoeker.repositories;

import com.woningzoeker.woningzoeker.models.Profiel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfielRepository extends JpaRepository<Profiel, Long> {
    List<Profiel> findByGebruikersnaam(String gebruikersnaam);
    List<Profiel> findByFavorieteHuizen(int favorieteHuizen);
    List<Profiel> findByInkomendeBerichten(String inkomendeBerichten);
    List<Profiel> findByUitgaandeBerichten(String uitgaandeBerichten);
}
