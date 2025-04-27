package com.woningzoeker.woningzoeker.services;

import com.woningzoeker.woningzoeker.models.Bieding;
import com.woningzoeker.woningzoeker.repositories.BiedingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BiedingService {

    private final BiedingRepository biedingRepository;

    public BiedingService(BiedingRepository biedingRepository) {
        this.biedingRepository = biedingRepository;
    }

    public Bieding save(Bieding bieding) {
        return biedingRepository.save(bieding);
    }
    public List<Bieding> saveAll(List<Bieding> biedingen) {
        return biedingRepository.saveAll(biedingen);
    }

    public List<Bieding> findAll() {
        return biedingRepository.findAll();
    }

    public Optional<Bieding> findById(Long id) {
        return biedingRepository.findById(id);
    }

    public boolean delete(Long id) {
        if (biedingRepository.existsById(id))
        {
            biedingRepository.deleteById(id);
            return true; // 204: succesvol verwijderd, geen body
        }
        return false; // 404: niet gevonden
    }
}
