package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.dtos.BiedingResponseDTO;
import com.woningzoeker.woningzoeker.mappers.BiedingMapper;
import com.woningzoeker.woningzoeker.models.Bieding;
import com.woningzoeker.woningzoeker.models.Huis;
import com.woningzoeker.woningzoeker.services.BiedingService;
import com.woningzoeker.woningzoeker.services.HuisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/bieding")
public class BiedingController {

    private final BiedingService biedingService;
    private final HuisService huisService;

    public BiedingController(BiedingService biedingService, HuisService huisService) {
        this.biedingService = biedingService;
        this.huisService = huisService;
    }

    // Haalt alle biedingen op of een bieding op basis van ID
    @GetMapping
    public ResponseEntity<List<BiedingResponseDTO>> getBieding(@RequestParam(required = false) Long id) {
        if (id == null) {
            List<Bieding> biedingen = biedingService.findAll();
            return ResponseEntity.ok(BiedingMapper.toResponseDTOList(biedingen));
        } else {
            return biedingService.findById(id)
                    .map(b -> ResponseEntity.ok(List.of(BiedingMapper.toResponseDTO(b))))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
    }

    // Creëert een nieuwe bieding
    @PostMapping
    public ResponseEntity<BiedingResponseDTO> postBieding(@RequestBody @Valid Bieding bieding) {
        Bieding opgeslagenBieding = biedingService.save(bieding);
        return ResponseEntity.status(HttpStatus.CREATED).body(BiedingMapper.toResponseDTO(opgeslagenBieding));
    }

    // Update een bieding
    @PutMapping("/{id}")
    public ResponseEntity<BiedingResponseDTO> updateBieding(@PathVariable Long id, @RequestBody @Valid Bieding bieding) {
        return biedingService.findById(id)
                .map(existingBieding -> {
                    // Update de andere velden
                    existingBieding.setBieder(bieding.getBieder());
                    existingBieding.setBod(bieding.getBod());
                    existingBieding.setPrijs(bieding.getPrijs());
                    existingBieding.setEigenaar(bieding.getEigenaar());
                    existingBieding.setEindDatum(bieding.getEindDatum());

                    // Haal het Huis object op aan de hand van huisId
                    Huis huis = huisService.findById(bieding.getHuis().getId())
                            .orElseThrow(() -> new RuntimeException("Huis niet gevonden met ID: " + bieding.getHuis().getId()));

                    existingBieding.setHuis(huis); // Verbind het Huis object met de Bieding

                    // Sla de Bieding op met het bijgewerkte Huis object
                    Bieding updatedBieding = biedingService.save(existingBieding);
                    return ResponseEntity.ok(BiedingMapper.toResponseDTO(updatedBieding));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Verwijder een bieding
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBieding(@PathVariable Long id) {
        return biedingService.delete(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
