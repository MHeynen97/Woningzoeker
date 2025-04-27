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

    @GetMapping
    public ResponseEntity<List<LocatieResponseDTO>> GetLocatieByWoonplaats(@RequestParam(required = false) String woonplaats) {
        List<Locatie> locaties = (woonplaats!=null) ? locatieService.findByWoonplaats(woonplaats) : locatieService.findAll();
        return ResponseEntity.ok(LocatieMapper.toResponseDTOList(locaties));
    }

    @GetMapping
    public ResponseEntity<List<LocatieResponseDTO>> getLocatie(@RequestParam(required = false) Long id){
        if (id == null) {
            var locaties = locatieService.findAll();
            return ResponseEntity.ok(LocatieMapper.toResponseDTOList(locaties));
        } else {
            return locatieService.findById(id)
                    .map(b -> List.of(LocatieMapper.toResponseDTO(b)))
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
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
