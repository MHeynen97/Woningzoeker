package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.dtos.HuisResponseDTO;
import com.woningzoeker.woningzoeker.mappers.HuisMapper;
import com.woningzoeker.woningzoeker.models.Huis;
import com.woningzoeker.woningzoeker.services.HuisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/huis")
public class HuisController {

    private final HuisService huisService;
    public HuisController(HuisService huisService) {
        this.huisService = huisService;
    }

    @GetMapping
    public ResponseEntity<List<HuisResponseDTO>> getHuis(@RequestParam(required = false) Long id){
        if (id == null) {
            var huizen = huisService.findAll();
            return ResponseEntity.ok(HuisMapper.toResponseDTOList(huizen));
        } else {
            return huisService.findById(id)
                    .map(b -> List.of(HuisMapper.toResponseDTO(b)))
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
    }

    @PostMapping
    public ResponseEntity<HuisResponseDTO> postHuis(@RequestBody Huis huis){
        Huis opgeslagenHuis = huisService.save(huis);
        return ResponseEntity.status(HttpStatus.CREATED).body(HuisMapper.toResponseDTO(opgeslagenHuis));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<HuisResponseDTO>> postHuizen(@RequestBody List<Huis> huis){
        List<Huis> opgeslagenHuizen = huisService.saveAll(huis);
        return ResponseEntity.status(HttpStatus.CREATED).body(HuisMapper.toResponseDTOList(opgeslagenHuizen));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HuisResponseDTO> updateHuis(@PathVariable Long id, @RequestBody Huis huis){
        var gevondenHuis = huisService.findById(id);
        if(gevondenHuis.isPresent()){
            Huis dbHuis = gevondenHuis.get();

            dbHuis.setAdres(huis.getAdres());
            dbHuis.setPrijs(huis.getPrijs());
            dbHuis.setAantalKamers(huis.getAantalKamers());
            dbHuis.setEnergieLabel(huis.getEnergieLabel());
            dbHuis.setFotos(huis.getFotos());
            dbHuis.setOmschrijving(huis.getOmschrijving());

            Huis updateHuis = huisService.save(dbHuis);
            return ResponseEntity.ok(HuisMapper.toResponseDTO(updateHuis));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HuisResponseDTO> deleteHuis(@PathVariable Long id) {
        var result = huisService.delete(id);
        if (result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
