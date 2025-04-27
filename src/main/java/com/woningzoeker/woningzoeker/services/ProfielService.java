package com.woningzoeker.woningzoeker.services;

import com.woningzoeker.woningzoeker.models.Profiel;
import com.woningzoeker.woningzoeker.repositories.ProfielRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfielService {

    private final ProfielRepository profielRepository;

    public ProfielService(ProfielRepository profielRepository) {
        this.profielRepository = profielRepository;
    }

    public Profiel save(Profiel profiel) {
        return profielRepository.save(profiel);
    }
    public List<Profiel> saveAll(List<Profiel> profielen) {
        return profielRepository.saveAll(profielen);
    }

    public List<Profiel> findAll() {
        return profielRepository.findAll();
    }

    public Optional<Profiel> findById(Long id) {
        return profielRepository.findById(id);
    }

    public boolean delete(Long id) {
        if (profielRepository.existsById(id))
        {
            profielRepository.deleteById(id);
            return true; // 204: succesvol verwijderd, geen body
        }
        return false; // 404: niet gevonden
    }

    public List<Profiel> findByGebruikersnaam(String gebruikersnaam) {
        return profielRepository.findByGebruikersnaam(gebruikersnaam);
    }
}

