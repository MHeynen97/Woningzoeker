package com.woningzoeker.woningzoeker.services;

import com.woningzoeker.woningzoeker.Utils.ZipUtils;
import com.woningzoeker.woningzoeker.exceptions.RecordNotFoundException;
import com.woningzoeker.woningzoeker.models.Huis;
import com.woningzoeker.woningzoeker.models.HuisFoto;
import com.woningzoeker.woningzoeker.repositories.HuisRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HuisDownloadService {

    private final HuisRepository huisRepository;
    private final PhotoService photoService;

    public HuisDownloadService(HuisRepository huisRepository, PhotoService photoService) {
        this.huisRepository = huisRepository;
        this.photoService = photoService;
    }

    public ResponseEntity<Resource> getHuisPhotosAsZip(Long id) {
        Huis huis = getHuisOfThrow(id);
        List<Resource> resources = getPhotoResources(huis);
        return buildZippedResponse(resources, id);
    }

    private Huis getHuisOfThrow(Long id) {
        return huisRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Huis met id: " + id + " kan niet gevonden worden."));
    }

    private List<Resource> getPhotoResources(Huis huis) {
        List<HuisFoto> fotos = huis.getHuisFoto();
        if (fotos == null || fotos.isEmpty()) {
            throw new RecordNotFoundException("Huis met id: " + huis.getId() + " heeft geen fotos");
        }
        return fotos.stream()
                .map(foto -> photoService.downLoadFile(foto.getFileName()))
                .collect(Collectors.toList());
    }

    private ResponseEntity<Resource> buildZippedResponse(List<Resource> resources, Long id) {
        try {
            ByteArrayResource zipResource = ZipUtils.zipFiles(resources);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"huis-fotos-" + id + ".zip\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(zipResource.contentLength())
                    .body(zipResource);
        } catch (IOException e) {
            throw new RuntimeException("Fout bij zippen van foto's", e);
        }
    }
}
