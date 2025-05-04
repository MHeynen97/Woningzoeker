package com.woningzoeker.woningzoeker.services;

import com.woningzoeker.woningzoeker.exceptions.RecordNotFoundException;
import com.woningzoeker.woningzoeker.models.Huis;
import com.woningzoeker.woningzoeker.models.HuisFoto;
import com.woningzoeker.woningzoeker.repositories.FileUploadRepository;
import com.woningzoeker.woningzoeker.repositories.HuisRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HuisService {
    private final HuisRepository huisRepository;
    private final FileUploadRepository uploadRepository;
    private final HuisDownloadService huisDownloadService;

    public HuisService(HuisRepository huisRepository, FileUploadRepository uploadRepository, HuisDownloadService huisDownloadService) {
        this.huisRepository = huisRepository;
        this.uploadRepository = uploadRepository;
        this.huisDownloadService = huisDownloadService;
    }

    public Huis save(Huis huis) {
        return huisRepository.save(huis);
    }

    public List<Huis> saveAll(List<Huis> huizen) {
        return huisRepository.saveAll(huizen);
    }

    public List<Huis> findAll() {
        return huisRepository.findAll();
    }

    public Optional<Huis> findById(Long id) {
        return huisRepository.findById(id);
    }

    @Transactional
    public ResponseEntity<Resource> getPhotoFromHuis(Long id) {
        return huisDownloadService.getHuisPhotosAsZip(id);
    }

    @Transactional
    public Huis assignPhotoToHuis(String filename, Long huisId) {
        Huis huis = huisRepository.findById(huisId)
                .orElseThrow(() -> new RecordNotFoundException("Huis met id " + huisId + " niet gevonden"));

        HuisFoto photo = uploadRepository.findByFileName(filename)
                .orElseThrow(() -> new RecordNotFoundException("Foto met naam '" + filename + "' niet gevonden"));

        if (huis.getHuisFotos() == null) {
            huis.setHuisFotos(new ArrayList<>());  // Of new HashSet<>(), afhankelijk van je type
        }

        huis.getHuisFotos().add(photo);
        return huisRepository.save(huis);
    }

    public boolean delete(Long id) {
        if (huisRepository.existsById(id)) {
            huisRepository.deleteById(id);
            return true; // 204: succesvol verwijderd, geen body
        }
        return false; // 404: niet gevonden
    }

}