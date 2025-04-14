package com.woningzoeker.woningzoeker.repositories;

import com.woningzoeker.woningzoeker.models.Bieding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BiedingRepository extends JpaRepository<Bieding, Integer> {
    List<Bieding> findByBiedingId(int biedingId);
    List<Bieding> findByPrijs(int prijs); // range van prijs instellen voor zoek criteria

}
