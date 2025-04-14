package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.models.Profiel;
import com.woningzoeker.woningzoeker.repositories.ProfielRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiel")
public class ProfielController {

    private final ProfielRepository profielRepository;
    public ProfielController(ProfielRepository profielRepository) {
        this.profielRepository = profielRepository;
    }

    @GetMapping
    public ResponseEntity<List<Profiel>> getProfiel(@RequestParam(required = false) String gebruikersnaam) {
        List<Profiel> profielen = (gebruikersnaam!=null) ? profielRepository.findByGebruikersnaam(gebruikersnaam) : profielRepository.findAll();
        return ResponseEntity.ok(profielen);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profiel> getProfielById(@PathVariable Long id) {
        return profielRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Profiel> postProfiel(@RequestBody Profiel profiel){
        Profiel opgeslagenProfiel = profielRepository.save(profiel);
        return ResponseEntity.status(HttpStatus.CREATED).body(opgeslagenProfiel);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Profiel>> postProfiel(@RequestBody List<Profiel> profiel){
        List<Profiel> opgeslagenProfielen = profielRepository.saveAll(profiel);
        return ResponseEntity.status(HttpStatus.CREATED).body(opgeslagenProfielen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profiel> updateProfiel(@PathVariable Long id, @RequestBody Profiel profiel){
        var gevondenProfiel = profielRepository.findById(id);
        if(gevondenProfiel.isPresent()){
            Profiel dbProfiel = gevondenProfiel.get();
            dbProfiel.setGebruikersnaam(profiel.getGebruikersnaam());
            dbProfiel.setNaam(profiel.getNaam());
            dbProfiel.setGeboortedatum(profiel.getGeboortedatum());
            dbProfiel.setEmail(profiel.getEmail());
            dbProfiel.setTelefoonnummer(profiel.getTelefoonnummer());
            dbProfiel.setOmschrijving(profiel.getOmschrijving());
            dbProfiel.setFavorieteHuizen(profiel.getFavorieteHuizen());
            dbProfiel.setInkomendeBerichten(profiel.getInkomendeBerichten());
            dbProfiel.setUitgaandeBerichten(profiel.getUitgaandeBerichten());
            profielRepository.save(dbProfiel);
            return ResponseEntity.ok(dbProfiel);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Profiel> deleteProfiel(@PathVariable Long id) {
        if (profielRepository.existsById(id)) {
            profielRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204: succesvol verwijderd, geen body
        }
        return ResponseEntity.notFound().build(); // 404: niet gevonden
    }

}
