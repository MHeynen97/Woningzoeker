package com.woningzoeker.woningzoeker.repositories;

import com.woningzoeker.woningzoeker.models.Profiel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfielRepository extends JpaRepository<Profiel, Integer> {
    List<Profiel> findByProfielname(String profielname);
    List<Profiel> findByFavorieteHuizen(String favorieteHuizen);
    List<Profiel> findByInkomendeBerichten(String inkomendeBerichten);
    List<Profiel> findByUitgaandeBerichten(String uitgaandeBerichten);
}
