package com.woningzoeker.woningzoeker.services;

import com.woningzoeker.woningzoeker.models.Gebruiker;
import com.woningzoeker.woningzoeker.repositories.GebruikerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GebruikerService {

    private final GebruikerRepository gebruikerRepository;

    public GebruikerService(GebruikerRepository gebruikerRepository) {
        this.gebruikerRepository = gebruikerRepository;
    }

    public Gebruiker save(Gebruiker gebruiker) {
        return gebruikerRepository.save(gebruiker);
    }

    public List<Gebruiker> saveAll(List<Gebruiker> gebruikers) {
        return  gebruikerRepository.saveAll(gebruikers);
    }

    public Optional<Gebruiker> findById(Long id) {
        return gebruikerRepository.findById(id);
    }

    public List<Gebruiker> findByGebruikersnaam(String gebruikersnaam) {
        return gebruikerRepository.findByGebruikersnaam(gebruikersnaam);
    }

    public boolean delete(Long id) {
        if (gebruikerRepository.existsById(id)) {
            gebruikerRepository.deleteById(id);
            return true; // 204: succesvol verwijderd, geen body
        }
        return false; // 404: niet gevonden
    }

    public List<Gebruiker> getGebruikers(String gebruikersnaam)
    {
        List<Gebruiker> gebruikers;
        //if else statement
        if (gebruikersnaam != null) {
            gebruikers = gebruikerRepository.findByGebruikersnaam(gebruikersnaam);
        } else {
            gebruikers = gebruikerRepository.findAll();
        }

        return gebruikers;
    }
}
