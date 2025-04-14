package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.models.Huis;
import com.woningzoeker.woningzoeker.repositories.HuisRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Huis")
public class HuisController {

    private final HuisRepository huisRepository;
    public HuisController(HuisRepository huisRepository) {
        this.huisRepository = huisRepository;
    }

    @GetMapping
    public ResponseEntity<List<Huis>> getHuis(@RequestParam(required = false) String adres) {
        List<Huis> Huizen = (adres!=null) ? huisRepository.findByAdres(adres) : huisRepository.findAll();
        return ResponseEntity.ok(Huizen);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Huis> getHuisById(@PathVariable Long id) {
        return huisRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Huis> postHuis(@RequestBody Huis huis){
        Huis opgeslagenHuis = huisRepository.save(huis);
        return ResponseEntity.status(HttpStatus.CREATED).body(opgeslagenHuis);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Huis>> postHuizen(@RequestBody List<Huis> huis){
        List<Huis> opgeslagenHuizen = huisRepository.saveAll(huis);
        return ResponseEntity.status(HttpStatus.CREATED).body(opgeslagenHuizen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Huis> updateHuis(@PathVariable Long id, @RequestBody Huis huis){
        var gevondenHuis = huisRepository.findById(id);
        if(gevondenHuis.isPresent()){
            Huis dbHuis = gevondenHuis.get();

            dbHuis.setAdres(huis.getAdres());
            dbHuis.setPrijs(huis.getPrijs());
            dbHuis.setAantalKamers(huis.getAantalKamers());
            dbHuis.setEnergieLabel(huis.getEnergieLabel());
            dbHuis.setFotos(huis.getFotos());
            dbHuis.setOmschrijving(huis.getOmschrijving());

            huisRepository.save(dbHuis);
            return ResponseEntity.ok(dbHuis);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Huis> deleteHuis(@PathVariable Long id) {
        if (huisRepository.existsById(id)) {
            huisRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204: succesvol verwijderd, geen body
        }
        return ResponseEntity.notFound().build(); // 404: niet gevonden
    }

}
