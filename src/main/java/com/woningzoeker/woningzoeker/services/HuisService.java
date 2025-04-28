package com.woningzoeker.woningzoeker.services;

import com.woningzoeker.woningzoeker.models.Huis;
import com.woningzoeker.woningzoeker.repositories.HuisRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HuisService {
    private final HuisRepository huisRepository;

    public HuisService(HuisRepository huisRepository) {
        this.huisRepository = huisRepository;
    }

    public Huis save(Huis huis) {
        return huisRepository.save(huis);
    }
    public List<Huis> saveAll(List<Huis> huizen) {
        return huisRepository.saveAll(huizen);
    }

    public List<Huis> findAll() {
        return huisRepository.findAll();
    }

    public Optional<Huis> findById(Long id) {
        return huisRepository.findById(id);
    }

    public boolean delete(Long id) {
        if (huisRepository.existsById(id))
        {
            huisRepository.deleteById(id);
            return true; // 204: succesvol verwijderd, geen body
        }
        return false; // 404: niet gevonden
    }
}