package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.dtos.BerichtResponseDTO;
import com.woningzoeker.woningzoeker.services.BerichtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/berichten")
public class BerichtController {

    private final BerichtService berichtService;

    public BerichtController(BerichtService berichtService) {
        this.berichtService = berichtService;
    }

    @PostMapping
    public ResponseEntity<BerichtResponseDTO> verzendBericht(@RequestBody BerichtResponseDTO dto) {
        BerichtResponseDTO response = berichtService.verzendBericht(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ontvangen/{gebruikerId}")
    public ResponseEntity<List<BerichtResponseDTO>> getInkomende(@PathVariable Long gebruikerId) {
        List<BerichtResponseDTO> berichten = berichtService.getInkomendeBerichten(gebruikerId);
        return ResponseEntity.ok(berichten);
    }

    @GetMapping("/verzonden/{gebruikerId}")
    public ResponseEntity<List<BerichtResponseDTO>> getUitgaande(@PathVariable Long gebruikerId) {
        List<BerichtResponseDTO> berichten = berichtService.getUitgaandeBerichten(gebruikerId);
        return ResponseEntity.ok(berichten);
    }
}
