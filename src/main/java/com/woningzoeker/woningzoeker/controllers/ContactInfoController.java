package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.dtos.ContactInfoResponseDTO;
import com.woningzoeker.woningzoeker.mappers.ContactInfoMapper;
import com.woningzoeker.woningzoeker.models.ContactInfo;
import com.woningzoeker.woningzoeker.services.ContactInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactinfo")
public class ContactInfoController {

    private final ContactInfoService contactInfoService;
    public ContactInfoController(ContactInfoService contactInfoService) {
        this.contactInfoService = contactInfoService;
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

    @PostMapping("/bulk")
    public ResponseEntity<List<ContactInfoResponseDTO>> postContactData(@RequestBody List<ContactInfo> contactInfo){
        List<ContactInfo> postContactData = contactInfoService.saveAll(contactInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(ContactInfoMapper.toResponseDTOList(postContactData));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactInfoResponseDTO> updateContactInfo(@PathVariable Long id, @RequestBody ContactInfo contactInfo){
        var gevondenContactInfo = contactInfoService.findById(id);
        if(gevondenContactInfo.isPresent()){
            ContactInfo dbContactInfo = gevondenContactInfo.get();

            dbContactInfo.setEmail(contactInfo.getEmail());
            dbContactInfo.setGebruikerId(contactInfo.getGebruikerId());
            dbContactInfo.setProfielId(contactInfo.getProfielId());
            dbContactInfo.setTelefoonnummer(contactInfo.getTelefoonnummer());

            ContactInfo updateContactInfo = contactInfoService.save(dbContactInfo);
            return ResponseEntity.ok(ContactInfoMapper.toResponseDTO(updateContactInfo));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContactInfoResponseDTO> deleteContactInfo(@PathVariable Long id) {
        var result = contactInfoService.delete(id);
        if (result){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
