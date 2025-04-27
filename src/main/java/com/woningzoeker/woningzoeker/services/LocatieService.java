package com.woningzoeker.woningzoeker.services;

import com.woningzoeker.woningzoeker.models.Locatie;
import com.woningzoeker.woningzoeker.repositories.LocatieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocatieService {

    private final LocatieRepository locatieRepository;

    public LocatieService(LocatieRepository locatieRepository) {
        this.locatieRepository = locatieRepository;
    }

    public Locatie save(Locatie locatie) {
        return locatieRepository.save(locatie);
    }
    public List<Locatie> saveAll(List<Locatie> locaties) {
        return locatieRepository.saveAll(locaties);
    }

    public List<Locatie> findAll() {
        return locatieRepository.findAll();
    }

    public Optional<Locatie> findById(Long id) {
        return locatieRepository.findById(id);
    }

    public boolean delete(Long id) {
        if (locatieRepository.existsById(id))
        {
            locatieRepository.deleteById(id);
            return true; // 204: succesvol verwijderd, geen body
        }
        return false; // 404: niet gevonden
    }

    public List<Locatie> findByWoonplaats(String woonplaats) {
        return locatieRepository.findByWoonplaats(woonplaats);
    }
}

