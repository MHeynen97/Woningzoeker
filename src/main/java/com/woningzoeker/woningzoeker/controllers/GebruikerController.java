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
@RequestMapping("/gebruikers")
public class GebruikerController {

    private final GebruikerService gebruikerService;

    public GebruikerController(GebruikerService gebruikerService) {
        this.gebruikerService = gebruikerService;
    }

    @GetMapping
    public ResponseEntity<List<GebruikerResponseDto>> getGebruikersbyGebruikersnaam(@RequestParam(required = false) String gebruikersnaam){
        List<Gebruiker> gebruikers = gebruikerService.getGebruikers(gebruikersnaam);
        return ResponseEntity.ok(GebruikerMapper.toResponseDTOList(gebruikers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<GebruikerResponseDto>> getGebruikers(@RequestParam(required = false) Long id){
        if (id == null) {
            var gebruikers = gebruikerService.findAll();
            return ResponseEntity.ok(GebruikerMapper.toResponseDTOList(gebruikers));
        } else {
            return gebruikerService.findById(id)
                    .map(b -> List.of(GebruikerMapper.toResponseDTO(b)))
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
    }

    @PostMapping
    public ResponseEntity<GebruikerResponseDto> postGebruiker(@RequestBody Gebruiker gebruiker){
        Gebruiker opgeslagenGebruiker = gebruikerService.save(gebruiker);
        return ResponseEntity.status(HttpStatus.CREATED).body(GebruikerMapper.toResponseDTO(opgeslagenGebruiker));
    }

    //ToDo check of gebruiker in list wel klopt op regel 52
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
            dbGebruiker.setEmail(gebruiker.getEmail());

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
