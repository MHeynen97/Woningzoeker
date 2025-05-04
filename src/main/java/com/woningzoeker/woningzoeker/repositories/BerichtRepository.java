package com.woningzoeker.woningzoeker.repositories;

import com.woningzoeker.woningzoeker.models.Bericht;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BerichtRepository extends JpaRepository<Bericht, Long> {
    List<Bericht> findByAfzenderId(Long afzenderId);
    List<Bericht> findByOntvangerId(Long ontvangerId);
}
