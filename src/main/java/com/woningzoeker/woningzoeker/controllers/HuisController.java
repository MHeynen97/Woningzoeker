package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.dtos.HuisResponseDTO;
import com.woningzoeker.woningzoeker.mappers.HuisMapper;
import com.woningzoeker.woningzoeker.models.Huis;
import com.woningzoeker.woningzoeker.services.HuisService;
import com.woningzoeker.woningzoeker.services.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/huis")
public class HuisController {

    private final HuisService huisService;
    private final PhotoService photoService;

    public HuisController(HuisService huisService, PhotoService photoService) {
        this.huisService = huisService;
        this.photoService = photoService;
    }

    // ToDo: Deze manier van getten (twee losse methodes) ook toepassen op de andere controllers.
    @GetMapping
    public ResponseEntity<List<HuisResponseDTO>> getAllHuizen() {
        List<Huis> huizen = huisService.findAll();
        return ResponseEntity.ok(HuisMapper.toResponseDTOList(huizen));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HuisResponseDTO> getHuisById(@PathVariable Long id) {
        return huisService.findById(id)
                .map(huis -> ResponseEntity.ok(HuisMapper.toResponseDTO(huis)))
                .orElse(ResponseEntity.notFound().build());
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

    @PostMapping("/{id}")
    public ResponseEntity<HuisResponseDTO> addPhotoToHuis(@PathVariable("id") Long id,
                                                     @RequestBody MultipartFile file)
            throws IOException
    {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/huis/")
                .path(Objects.requireNonNull(id.toString()))
                .path("/photo")
                .toUriString();
        String fileName = photoService.storeFile(file);
        Huis huis = huisService.assignPhotoToHuis(fileName, id);

        return ResponseEntity.created(URI.create(url)).body(HuisMapper.toResponseDTO(huis));

    }

    @PutMapping("/{id}")
    public ResponseEntity<HuisResponseDTO> updateHuis(@PathVariable Long id, @RequestBody Huis huis) {
        var gevondenHuis = huisService.findById(id);
        if (gevondenHuis.isPresent()) {
            Huis dbHuis = gevondenHuis.get();

            // Werk velden bij als ze aanwezig zijn
            if (huis.getAdres() != null) {
                dbHuis.setAdres(huis.getAdres());
            }
            if (huis.getPrijs() != 0) {
                dbHuis.setPrijs(huis.getPrijs());
            }
            if (huis.getAantalKamers() != 0) {
                dbHuis.setAantalKamers(huis.getAantalKamers());
            }
            if (huis.getEnergieLabel() != null) {
                dbHuis.setEnergieLabel(huis.getEnergieLabel());
            }
            if (huis.getHuisFoto() != null) {
                dbHuis.setHuisFoto(huis.getHuisFoto());
            }
            if (huis.getOmschrijving() != null) {
                dbHuis.setOmschrijving(huis.getOmschrijving());
            }

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
