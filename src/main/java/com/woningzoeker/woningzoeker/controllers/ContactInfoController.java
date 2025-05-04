package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.dtos.ContactInfoResponseDTO;
import com.woningzoeker.woningzoeker.mappers.ContactInfoMapper;
import com.woningzoeker.woningzoeker.models.ContactInfo;
import com.woningzoeker.woningzoeker.models.Gebruiker;
import com.woningzoeker.woningzoeker.models.Profiel;
import com.woningzoeker.woningzoeker.services.ContactInfoService;
import com.woningzoeker.woningzoeker.services.GebruikerService;
import com.woningzoeker.woningzoeker.services.ProfielService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// ToDo nadenken over logging toevoegen. Nice to have indien er tijd over is.

@RestController
@RequestMapping("/contactinfo")
public class ContactInfoController {

    private final ContactInfoService contactInfoService;
    private final GebruikerService gebruikerService;
    private final ProfielService profielService;
    public ContactInfoController(ContactInfoService contactInfoService, GebruikerService gebruikerService, ProfielService profielService) {
        this.contactInfoService = contactInfoService;
        this.gebruikerService = gebruikerService;
        this.profielService = profielService;
    }

    @GetMapping
    public ResponseEntity<List<ContactInfoResponseDTO>> getContactInfo(@RequestParam(required = false) Long id){
        if (id == null) {
            var contactInfos = contactInfoService.findAll();
            return ResponseEntity.ok(ContactInfoMapper.toResponseDTOList(contactInfos));
        } else {
            return contactInfoService.findById(id)
                    .map(b -> List.of(ContactInfoMapper.toResponseDTO(b)))
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
    }

    @PostMapping
    public ResponseEntity<ContactInfoResponseDTO> postContactInfo(@RequestBody ContactInfo contactInfo){
        ContactInfo opgeslagenContactInfo = contactInfoService.save(contactInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(ContactInfoMapper.toResponseDTO(opgeslagenContactInfo));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ContactInfoResponseDTO> updateContactInfo(@PathVariable Long id, @RequestBody ContactInfo contactInfo) {
        var gevondenContactInfo = contactInfoService.findById(id);
        if (gevondenContactInfo.isPresent()) {
            ContactInfo dbContactInfo = gevondenContactInfo.get();

            if (contactInfo.getEmail() != null) {
                dbContactInfo.setEmail(contactInfo.getEmail());
            }
            if (contactInfo.getTelefoonnummer() != null) {
                dbContactInfo.setTelefoonnummer(contactInfo.getTelefoonnummer());
            }

            if (contactInfo.getProfiel() != null && contactInfo.getProfiel().getId() != 0) {
                Optional<Profiel> profielOpt = profielService.findById(contactInfo.getProfiel().getId());
                profielOpt.ifPresent(dbContactInfo::setProfiel);
            }

            ContactInfo updateContactInfo = contactInfoService.save(dbContactInfo);
            return ResponseEntity.ok(ContactInfoMapper.toResponseDTO(updateContactInfo));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContactInfo(@PathVariable Long id) {
        var result = contactInfoService.delete(id);
        if (result){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact info met ID " + id + " niet gevonden.");
        }
    }

}
