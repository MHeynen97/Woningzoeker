package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.dtos.GebruikerResponseDto;
import com.woningzoeker.woningzoeker.mappers.GebruikerMapper;
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
    public ResponseEntity<List<GebruikerResponseDto>> getGebruikers(
            @RequestParam(required = false) String gebruikersnaam){

        List<Gebruiker> gebruikers;
        //if else statement
        if (gebruikersnaam != null) {
            gebruikers = gebruikerRepository.findByGebruikersnaam(gebruikersnaam);
        } else {
            gebruikers = gebruikerRepository.findAll();
        }

        return ResponseEntity.ok(GebruikerMapper.ToResponseDTOList(gebruikers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GebruikerResponseDto> getGebruikerById(@PathVariable Long id) {
        return gebruikerRepository.findById(id)
                .map(GebruikerMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GebruikerResponseDto> postGebruiker(@RequestBody Gebruiker gebruiker){
        Gebruiker opgeslagenGebruiker = gebruikerRepository.save(gebruiker);
        return ResponseEntity.status(HttpStatus.CREATED).body(GebruikerMapper.toResponseDTO(opgeslagenGebruiker));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<GebruikerResponseDto>> postGebruikers(@RequestBody List<Gebruiker> gebruikers) {
        List<Gebruiker> opgeslagenGebruikers = gebruikerRepository.saveAll(gebruikers);
        return ResponseEntity.status(HttpStatus.CREATED).body(GebruikerMapper.ToResponseDTOList(opgeslagenGebruikers));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GebruikerResponseDto> updateGebruiker(@PathVariable Long id, @RequestBody Gebruiker gebruiker){
        var gevondenGebruiker = gebruikerRepository.findById(id);
        if(gevondenGebruiker.isPresent()){
            Gebruiker dbGebruiker = gevondenGebruiker.get();
            dbGebruiker.setGebruikersnaam(gebruiker.getGebruikersnaam());
            dbGebruiker.setEmail(gebruiker.getEmail());
            Gebruiker updateGebruiker = gebruikerRepository.save(dbGebruiker);
            return ResponseEntity.ok(GebruikerMapper.toResponseDTO(updateGebruiker));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGebruiker(@PathVariable Long id) {
        if (gebruikerRepository.existsById(id)) {
            gebruikerRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204: succesvol verwijderd, geen body
        }
        return ResponseEntity.notFound().build(); // 404: niet gevonden
    }
}
