package com.woningzoeker.woningzoeker.services;

import com.woningzoeker.woningzoeker.models.HuisFoto;
import com.woningzoeker.woningzoeker.repositories.FileUploadRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class PhotoService {
    private final Path rootLocation = Paths.get("upload-dir");
    private final Path fileStoragePath;
    private final String fileStorageLocation;
    private final FileUploadRepository repo;

    public PhotoService(@Value("${my.upload_location}") String fileStorageLocation, FileUploadRepository repo) throws IOException {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        this.fileStorageLocation = fileStorageLocation;
        this.repo = repo;

        Files.createDirectories(fileStoragePath);


    }

    public String storeFile(MultipartFile file) throws IOException{

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        repo.save(new HuisFoto(fileName));
        return fileName;
    }

    public Resource downLoadFile(String fileName) {
        try {
            Path filePath = Paths.get("upload-dir").resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Bestand niet gevonden: " + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Fout bij het ophalen van het bestand: " + fileName, e);
        }
    }
}
