package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.models.Gebruiker;
import com.woningzoeker.woningzoeker.repositories.GebruikerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gebruikers")
public class GebruikerController {

    private final GebruikerRepository gebruikerRepository;
    public GebruikerController(GebruikerRepository gebruikerRepository) {
        this.gebruikerRepository = gebruikerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Gebruiker>> getGebruiker(@RequestParam(required = false) String gebruikersnaam){
        //if else statement
        List<Gebruiker> gebruikers = (gebruikersnaam!=null) ? gebruikerRepository.findByGebruikersnaam(gebruikersnaam) : gebruikerRepository.findAll();
        return ResponseEntity.ok(gebruikers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gebruiker> getGebruikerById(@PathVariable Long id) {
        return gebruikerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // is dit niet dubbelop met de andere getmapping waar gebruikersnaam mee gegeven kan worden in de url?
    @GetMapping("/naam/{gebruikersnaam}")
    public ResponseEntity<Gebruiker> getGebruikerByGebruikersnaam(@PathVariable String gebruikersnaam) {
        return gebruikerRepository.findByGebruikersnaam(gebruikersnaam)
                .stream()
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Gebruiker> postGebruiker(@RequestBody Gebruiker gebruiker){
        Gebruiker opgeslagenGebruiker = gebruikerRepository.save(gebruiker);
        return ResponseEntity.status(HttpStatus.CREATED).body(opgeslagenGebruiker);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Gebruiker>> postGebruikers(@RequestBody List<Gebruiker> gebruikers) {
        List<Gebruiker> opgeslagenGebruikers = gebruikerRepository.saveAll(gebruikers);
        return ResponseEntity.status(HttpStatus.CREATED).body(opgeslagenGebruikers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gebruiker> updateGebruiker(@PathVariable Long id, @RequestBody Gebruiker gebruiker){
        var gevondenGebruiker = gebruikerRepository.findById(id);
        if(gevondenGebruiker.isPresent()){
            Gebruiker dbGebruiker = gevondenGebruiker.get();
            dbGebruiker.setGebruikersnaam(gebruiker.getGebruikersnaam());
            dbGebruiker.setEmail(gebruiker.getEmail());
            gebruikerRepository.save(dbGebruiker);
            return ResponseEntity.ok(dbGebruiker);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Gebruiker> deleteGebruiker(@PathVariable Long id) {
        if (gebruikerRepository.existsById(id)) {
            gebruikerRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204: succesvol verwijderd, geen body
        }
        return ResponseEntity.notFound().build(); // 404: niet gevonden
    }
}
