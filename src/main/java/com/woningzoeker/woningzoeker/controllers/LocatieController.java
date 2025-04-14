package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.models.Locatie;
import com.woningzoeker.woningzoeker.repositories.LocatieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locatie")
public class LocatieController {

    private final LocatieRepository locatieRepository;
    public LocatieController(LocatieRepository locatieRepository) {
        this.locatieRepository = locatieRepository;
    }

    @GetMapping
    public ResponseEntity<List<Locatie>> getLocatie(@RequestParam(required = false) String woonplaats) {
        List<Locatie> locaties = (woonplaats!=null) ? locatieRepository.findByWoonplaats(woonplaats) : locatieRepository.findAll();
        return ResponseEntity.ok(locaties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Locatie> getLocatieById(@PathVariable Long id) {
        return locatieRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Locatie> postLocatie(@RequestBody Locatie locatie){
        Locatie opgeslagenLocatie = locatieRepository.save(locatie);
        return ResponseEntity.status(HttpStatus.CREATED).body(opgeslagenLocatie);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Locatie>> postLocaties(@RequestBody List<Locatie> locatie){
        List<Locatie> opgeslagenLocaties = locatieRepository.saveAll(locatie);
        return ResponseEntity.status(HttpStatus.CREATED).body(opgeslagenLocaties);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locatie> updatelocatie(@PathVariable Long id, @RequestBody Locatie locatie){
        var gevondenlocatie = locatieRepository.findById(id);
        if(gevondenlocatie.isPresent()){
            Locatie dbLocatie = gevondenlocatie.get();

            dbLocatie.setAdres(locatie.getAdres());
            dbLocatie.setPostcode(locatie.getPostcode());
            dbLocatie.setWoonplaats(locatie.getWoonplaats());

            locatieRepository.save(dbLocatie);
            return ResponseEntity.ok(dbLocatie);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Locatie> deletelocatie(@PathVariable Long id) {
        if (locatieRepository.existsById(id)) {
            locatieRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204: succesvol verwijderd, geen body
        }
        return ResponseEntity.notFound().build(); // 404: niet gevonden
    }

}
