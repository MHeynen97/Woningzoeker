package com.woningzoeker.woningzoeker.controllers;

import com.woningzoeker.woningzoeker.models.ContactInfo;
import com.woningzoeker.woningzoeker.repositories.ContactInfoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ContactInfo")
public class ContactInfoController {

    private final ContactInfoRepository contactInfoRepository;
    public ContactInfoController(ContactInfoRepository contactInfoRepository) {
        this.contactInfoRepository = contactInfoRepository;
    }

    @GetMapping
    public ResponseEntity<List<ContactInfo>> getContactInfo() {
        List<ContactInfo> contactData = contactInfoRepository.findAll();
        return ResponseEntity.ok(contactData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactInfo> getContactInfoById(@PathVariable Long id) {
        return contactInfoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContactInfo> postContactInfo(@RequestBody ContactInfo contactInfo){
        ContactInfo opgeslagenContactInfo = contactInfoRepository.save(contactInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(opgeslagenContactInfo);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ContactInfo>> postContactData(@RequestBody List<ContactInfo> contactInfo){
        List<ContactInfo> postContactData = contactInfoRepository.saveAll(contactInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(postContactData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactInfo> updateContactInfo(@PathVariable Long id, @RequestBody ContactInfo contactInfo){
        var gevondenContactInfo = contactInfoRepository.findById(id);
        if(gevondenContactInfo.isPresent()){
            ContactInfo dbContactInfo = gevondenContactInfo.get();

            dbContactInfo.setEmail(contactInfo.getEmail());
            dbContactInfo.setGebruikerId(contactInfo.getGebruikerId());
            dbContactInfo.setProfielId(contactInfo.getProfielId());

            contactInfoRepository.save(dbContactInfo);
            return ResponseEntity.ok(dbContactInfo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContactInfo> deleteContactInfo(@PathVariable Long id) {
        if (contactInfoRepository.existsById(id)) {
            contactInfoRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204: succesvol verwijderd, geen body
        }
        return ResponseEntity.notFound().build(); // 404: niet gevonden
    }

}
