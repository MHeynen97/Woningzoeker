package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.dtos.GebruikerResponseDto;
import com.woningzoeker.woningzoeker.mappers.GebruikerMapper;
import com.woningzoeker.woningzoeker.models.Gebruiker;
import com.woningzoeker.woningzoeker.services.GebruikerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gebruiker")
public class GebruikerController {

    private final GebruikerService gebruikerService;

    public GebruikerController(GebruikerService gebruikerService) {
        this.gebruikerService = gebruikerService;
    }

    @GetMapping
    public ResponseEntity<List<GebruikerResponseDto>> getGebruikers(@RequestParam(required = false) String gebruikersnaam){
        return ResponseEntity.ok(GebruikerMapper.toResponseDTOList(gebruikerService.getGebruikers(gebruikersnaam)));
    }

    @GetMapping("/{id}")

    public ResponseEntity<GebruikerResponseDto> getGebruikerById(@PathVariable long id) {
        return gebruikerService.findById(id)
                .map(GebruikerMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GebruikerResponseDto> postGebruiker(@RequestBody Gebruiker gebruiker){
        Gebruiker opgeslagenGebruiker = gebruikerService.save(gebruiker);
        return ResponseEntity.status(HttpStatus.CREATED).body(GebruikerMapper.toResponseDTO(opgeslagenGebruiker));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<GebruikerResponseDto>> postGebruikers(@RequestBody List<Gebruiker> gebruikers) {
        List<Gebruiker> opgeslagenGebruikers = gebruikerService.saveAll(gebruikers);
        return ResponseEntity.status(HttpStatus.CREATED).body(GebruikerMapper.toResponseDTOList(opgeslagenGebruikers));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GebruikerResponseDto> updateGebruiker(@PathVariable long id, @RequestBody Gebruiker gebruiker){
        var gevondenGebruiker = gebruikerService.findById(id);
        if(gevondenGebruiker.isPresent()){
            Gebruiker dbGebruiker = gevondenGebruiker.get();
            dbGebruiker.setGebruikersnaam(gebruiker.getGebruikersnaam());
            Gebruiker updateGebruiker = gebruikerService.save(dbGebruiker);
            return ResponseEntity.ok(GebruikerMapper.toResponseDTO(updateGebruiker));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGebruiker(@PathVariable long id) {
        var result = gebruikerService.delete(id);
        if (result){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
