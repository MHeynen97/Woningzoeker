package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.dtos.BiedingResponseDTO;
import com.woningzoeker.woningzoeker.dtos.ProfielResponseDTO;
import com.woningzoeker.woningzoeker.mappers.BiedingMapper;
import com.woningzoeker.woningzoeker.mappers.ProfielMapper;
import com.woningzoeker.woningzoeker.models.Profiel;
import com.woningzoeker.woningzoeker.repositories.ProfielRepository;
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

            dbProfiel.setGebruikersnaam(profiel.getGebruikersnaam());
            dbProfiel.setNaam(profiel.getNaam());
            dbProfiel.setGeboortedatum(profiel.getGeboortedatum());
            dbProfiel.setEmail(profiel.getEmail());
            dbProfiel.setTelefoonnummer(profiel.getTelefoonnummer());
            dbProfiel.setOmschrijving(profiel.getOmschrijving());
            dbProfiel.setFavorieteHuizen(profiel.getFavorieteHuizen());
            dbProfiel.setInkomendeBerichten(profiel.getInkomendeBerichten());
            dbProfiel.setUitgaandeBerichten(profiel.getUitgaandeBerichten());

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
