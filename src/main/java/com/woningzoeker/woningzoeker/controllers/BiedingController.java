package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.dtos.BiedingResponseDTO;
import com.woningzoeker.woningzoeker.mappers.BiedingMapper;
import com.woningzoeker.woningzoeker.models.Bieding;
import com.woningzoeker.woningzoeker.services.BiedingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/bieding")
public class BiedingController {

    private final BiedingService biedingService;

    public BiedingController(BiedingService biedingService) {
        this.biedingService = biedingService;
    }

    @GetMapping
    public ResponseEntity<List<BiedingResponseDTO>> getBieding(@RequestParam(required = false) Long id){
        if (id == null) {
            var biedingen = biedingService.findAll();
            return ResponseEntity.ok(BiedingMapper.toResponseDTOList(biedingen));
        } else {
            return biedingService.findById(id)
                    .map(b -> List.of(BiedingMapper.toResponseDTO(b)))
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BiedingResponseDTO> getBiedingById(@PathVariable Long id) {
        return biedingService.findById(id)
                .map(BiedingMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BiedingResponseDTO> postBieding(@RequestBody @Valid Bieding bieding){
        Bieding opgeslagenBieding = biedingService.save(bieding);
        return ResponseEntity.status(HttpStatus.CREATED).body(BiedingMapper.toResponseDTO(opgeslagenBieding));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<BiedingResponseDTO>> postBiedingen(@RequestBody List<Bieding> biedingen) {
        List<Bieding> opgeslagenBiedingen = biedingService.saveAll(biedingen);
        return ResponseEntity.status(HttpStatus.CREATED).body(BiedingMapper.toResponseDTOList(opgeslagenBiedingen));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BiedingResponseDTO> updateBieding(@PathVariable Long id, @RequestBody Bieding bieding){
        var gevondenBieding = biedingService.findById(id);
        if(gevondenBieding.isPresent()){
            Bieding dbBieding = gevondenBieding.get();

            dbBieding.setBieder(bieding.getBieder());
            dbBieding.setBod(bieding.getBod());
            dbBieding.setPrijs(bieding.getPrijs());
            dbBieding.setEigenaar(bieding.getEigenaar());
            dbBieding.setEindDatum(bieding.getEindDatum());
            dbBieding.setHuisId(bieding.getHuisId());

            Bieding updateBieding = biedingService.save(dbBieding);
            return ResponseEntity.ok(BiedingMapper.toResponseDTO(updateBieding));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBieding(@PathVariable Long id) {
        var result = biedingService.delete(id);
        if (result){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
