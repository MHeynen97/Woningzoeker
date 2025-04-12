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
        List<Gebruiker> gebruikers = (gebruikersnaam!=null) ? gebruikerRepository.findByGebruiker(gebruikersnaam) : gebruikerRepository.findAll();
        return ResponseEntity.ok(gebruikers);
    }

    @PostMapping
    public ResponseEntity<Gebruiker> posGebruiker(@RequestBody Gebruiker gebruiker){
        Gebruiker opgeslagenGebruiker = gebruikerRepository.save(gebruiker);
        return ResponseEntity.status(HttpStatus.CREATED).body(opgeslagenGebruiker);
    }
    //@PutMapping

    //@DeleteMapping
}
