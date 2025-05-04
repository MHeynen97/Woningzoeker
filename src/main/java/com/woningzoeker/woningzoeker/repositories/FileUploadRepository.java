package com.woningzoeker.woningzoeker.repositories;

import com.woningzoeker.woningzoeker.models.HuisFoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<HuisFoto, String> {
    Optional<HuisFoto> findByFileName(String fileName);
}
