package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.dtos.LocatieResponseDTO;
import com.woningzoeker.woningzoeker.mappers.LocatieMapper;
import com.woningzoeker.woningzoeker.models.Locatie;
import com.woningzoeker.woningzoeker.services.LocatieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locatie")
public class LocatieController {

    private final LocatieService locatieService;

    public LocatieController(LocatieService locatieService) {
        this.locatieService = locatieService;
    }

    @GetMapping()
    public ResponseEntity<List<LocatieResponseDTO>> getLocatie() {
        List<Locatie> locaties = locatieService.findAll();
        return ResponseEntity.ok(LocatieMapper.toResponseDTOList(locaties));
    }

    @GetMapping("/woonplaats")
    public ResponseEntity<List<LocatieResponseDTO>> getLocatiesByWoonplaats(@RequestParam String woonplaats) {
        if (woonplaats != null && !woonplaats.isEmpty()) {
            List<Locatie> locaties = locatieService.findByWoonplaats(woonplaats);
            if (locaties.isEmpty()) {
                // Als er geen locaties gevonden zijn, geef een 404-status met een bericht
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);  // Je kunt een foutmelding toevoegen als je dat wilt in de body.
            }
            // Als er locaties zijn gevonden, geef ze terug
            return ResponseEntity.ok(LocatieMapper.toResponseDTOList(locaties));
        }
        // Als de woonplaats null of leeg is, geef een foutmelding
        return ResponseEntity.badRequest().body(null);  // Of je zou een foutbericht kunnen toevoegen aan de body
    }


    @GetMapping("/{id}")
    public ResponseEntity<LocatieResponseDTO> getLocatieById(@PathVariable Long id) {
        return locatieService.findById(id)
                .map(b -> ResponseEntity.ok(LocatieMapper.toResponseDTO(b)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LocatieResponseDTO> postLocatie(@RequestBody Locatie locatie){
        Locatie opgeslagenLocatie = locatieService.save(locatie);
        return ResponseEntity.status(HttpStatus.CREATED).body(LocatieMapper.toResponseDTO(opgeslagenLocatie));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<LocatieResponseDTO>> postLocaties(@RequestBody List<Locatie> locatie){
        List<Locatie> opgeslagenLocaties = locatieService.saveAll(locatie);
        return ResponseEntity.status(HttpStatus.CREATED).body(LocatieMapper.toResponseDTOList(opgeslagenLocaties));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocatieResponseDTO> updatelocatie(@PathVariable Long id, @RequestBody Locatie locatie){
        var gevondenlocatie = locatieService.findById(id);
        if(gevondenlocatie.isPresent()){
            Locatie dbLocatie = gevondenlocatie.get();

            dbLocatie.setAdres(locatie.getAdres());
            dbLocatie.setPostcode(locatie.getPostcode());
            dbLocatie.setWoonplaats(locatie.getWoonplaats());

            Locatie updateLocatie = locatieService.save(dbLocatie);
            return ResponseEntity.ok(LocatieMapper.toResponseDTO(updateLocatie));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocatie(@PathVariable Long id) {
        var result = locatieService.delete(id);
        if (result){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
