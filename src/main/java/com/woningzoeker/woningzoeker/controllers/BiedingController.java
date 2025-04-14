package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.models.Bieding;
import com.woningzoeker.woningzoeker.repositories.BiedingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Biedings")
public class BiedingController {

    private final BiedingRepository biedingRepository;
    public BiedingController(BiedingRepository biedingRepository) {
        this.biedingRepository = biedingRepository;
    }

    @GetMapping
    public ResponseEntity<List<Bieding>> getBieding(@RequestParam(required = false) Long id){
        //if else statement
        List<Bieding> biedingen = biedingRepository.findByBiedingId(id);
        return ResponseEntity.ok(biedingen);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bieding> getBiedingById(@PathVariable Long id) {
        return biedingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Bieding> postBieding(@RequestBody Bieding bieding){
        Bieding opgeslagenBieding = biedingRepository.save(bieding);
        return ResponseEntity.status(HttpStatus.CREATED).body(opgeslagenBieding);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Bieding>> postBiedingen(@RequestBody List<Bieding> biedingen) {
        List<Bieding> opgeslagenBiedingen = biedingRepository.saveAll(biedingen);
        return ResponseEntity.status(HttpStatus.CREATED).body(opgeslagenBiedingen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bieding> updateBieding(@PathVariable Long id, @RequestBody Bieding bieding){
        var gevondenBieding = biedingRepository.findById(id);
        if(gevondenBieding.isPresent()){
            Bieding dbBieding = gevondenBieding.get();

            dbBieding.setBieder(bieding.getBieder());
            dbBieding.setBod(bieding.getBod());
            dbBieding.setPrijs(bieding.getPrijs());
            dbBieding.setEigenaar(bieding.getEigenaar());
            dbBieding.setEindDatum(bieding.getEindDatum());
            dbBieding.setHuisId(bieding.getHuisId());

            biedingRepository.save(dbBieding);
            return ResponseEntity.ok(dbBieding);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Bieding> deleteBieding(@PathVariable Long id) {
        if (biedingRepository.existsById(id)) {
            biedingRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204: succesvol verwijderd, geen body
        }
        return ResponseEntity.notFound().build(); // 404: niet gevonden
    }
}
