package com.woningzoeker.woningzoeker.services;

import com.woningzoeker.woningzoeker.models.ContactInfo;
import com.woningzoeker.woningzoeker.repositories.ContactInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactInfoService {
    private final ContactInfoRepository contactInfoRepository;

    public ContactInfoService(ContactInfoRepository contactInfoRepository) {
        this.contactInfoRepository = contactInfoRepository;
    }

    public ContactInfo save(ContactInfo contactInfo) {
        return contactInfoRepository.save(contactInfo);
    }
    public List<ContactInfo> saveAll(List<ContactInfo> contactInfo) {
        return contactInfoRepository.saveAll(contactInfo);
    }

    public List<ContactInfo> findAll() {
        return contactInfoRepository.findAll();
    }

    public Optional<ContactInfo> findById(Long id) {
        return contactInfoRepository.findById(id);
    }

    public boolean delete(Long id) {
        if (contactInfoRepository.existsById(id))
        {
            contactInfoRepository.deleteById(id);
            return true; // 204: succesvol verwijderd, geen body
        }
        return false; // 404: niet gevonden
    }
}
