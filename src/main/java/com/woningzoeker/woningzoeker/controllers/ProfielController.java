package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.dtos.ProfielResponseDTO;
import com.woningzoeker.woningzoeker.mappers.ProfielMapper;
import com.woningzoeker.woningzoeker.models.Profiel;
import com.woningzoeker.woningzoeker.services.ProfielService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiel")
public class ProfielController {

    private final ProfielService profielService;
    public ProfielController(ProfielService profielService) {
        this.profielService = profielService;
    }

    @GetMapping
    public ResponseEntity<List<ProfielResponseDTO>> getProfiel(@RequestParam(required = false) String gebruikersnaam) {
        List<Profiel> profielen = (gebruikersnaam!=null) ? profielService.findByGebruikersnaam(gebruikersnaam) : profielService.findAll();
        return ResponseEntity.ok(ProfielMapper.toResponseDTOList(profielen));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ProfielResponseDTO>> getProfiel(@RequestParam(required = false) Long id){
        if (id == null) {
            var profiel = profielService.findAll();
            return ResponseEntity.ok(ProfielMapper.toResponseDTOList(profiel));
        } else {
            return profielService.findById(id)
                    .map(b -> List.of(ProfielMapper.toResponseDTO(b)))
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
    }
    @PostMapping
    public ResponseEntity<ProfielResponseDTO> postProfiel(@RequestBody Profiel profiel){
        Profiel opgeslagenProfiel = profielService.save(profiel);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProfielMapper.toResponseDTO(opgeslagenProfiel));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ProfielResponseDTO>> postProfiel(@RequestBody List<Profiel> profiel){
        List<Profiel> opgeslagenProfielen = profielService.saveAll(profiel);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProfielMapper.toResponseDTOList(opgeslagenProfielen));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfielResponseDTO> updateProfiel(@PathVariable Long id, @RequestBody Profiel profiel){
        var gevondenProfiel = profielService.findById(id);
        if(gevondenProfiel.isPresent()){
            Profiel dbProfiel = gevondenProfiel.get();

            dbProfiel.setNaam(profiel.getNaam());
            dbProfiel.setGeboortedatum(profiel.getGeboortedatum());
            dbProfiel.setOmschrijving(profiel.getOmschrijving());

            dbProfiel.setFavorieteHuizen(profiel.getFavorieteHuizen());
            // ContactInfo bijwerken
            if (profiel.getContactInfo() != null) {
                if (dbProfiel.getContactInfo() == null) {
                    dbProfiel.setContactInfo(profiel.getContactInfo());
                } else {
                    dbProfiel.getContactInfo().setEmail(profiel.getContactInfo().getEmail());
                    dbProfiel.getContactInfo().setTelefoonnummer(profiel.getContactInfo().getTelefoonnummer());
                }
            }

            // Locatie bijwerken
            if (profiel.getLocatie() != null) {
                if (dbProfiel.getLocatie() == null) {
                    dbProfiel.setLocatie(profiel.getLocatie());
                } else {
                    dbProfiel.getLocatie().setWoonplaats(profiel.getLocatie().getWoonplaats());
                }
            }

            Profiel updateProfiel = profielService.save(dbProfiel);

            return ResponseEntity.ok(ProfielMapper.toResponseDTO(updateProfiel));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfiel(@PathVariable Long id) {
        var result = profielService.delete(id);
        if (result){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
