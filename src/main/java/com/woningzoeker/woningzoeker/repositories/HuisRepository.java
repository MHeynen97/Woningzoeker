package com.woningzoeker.woningzoeker.repositories;

import com.woningzoeker.woningzoeker.models.Huis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HuisRepository extends JpaRepository<Huis, Long> {
    List<Huis> findByAdres(String Adres);

}
