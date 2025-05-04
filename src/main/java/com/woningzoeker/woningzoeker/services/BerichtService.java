package com.woningzoeker.woningzoeker.services;

import com.woningzoeker.woningzoeker.dtos.BerichtResponseDTO;
import com.woningzoeker.woningzoeker.mappers.BerichtMapper;
import com.woningzoeker.woningzoeker.models.Bericht;
import com.woningzoeker.woningzoeker.models.Gebruiker;
import com.woningzoeker.woningzoeker.repositories.BerichtRepository;
import com.woningzoeker.woningzoeker.repositories.GebruikerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BerichtService {

    private final BerichtRepository berichtRepository;
    private final GebruikerRepository gebruikerRepository;

    public BerichtService(BerichtRepository berichtRepository, GebruikerRepository gebruikerRepository) {
        this.berichtRepository = berichtRepository;
        this.gebruikerRepository = gebruikerRepository;
    }

    public BerichtResponseDTO verzendBericht(BerichtResponseDTO dto) {
        Bericht bericht = BerichtMapper.toEntity(dto);
        Gebruiker afzender = gebruikerRepository.findById(dto.getAfzenderId())
                .orElseThrow(() -> new RuntimeException("Afzender niet gevonden"));
        Gebruiker ontvanger = gebruikerRepository.findById(dto.getOntvangerId())
                .orElseThrow(() -> new RuntimeException("Ontvanger niet gevonden"));

        bericht.setAfzender(afzender);
        bericht.setOntvanger(ontvanger);
        bericht.setVerzondenOp(LocalDateTime.now());

        Bericht opgeslagen = berichtRepository.save(bericht);
        return BerichtMapper.toDTO(opgeslagen);
    }

    public List<BerichtResponseDTO> getInkomendeBerichten(Long gebruikerId) {
        return berichtRepository.findByOntvangerId(gebruikerId)
                .stream()
                .map(BerichtMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<BerichtResponseDTO> getUitgaandeBerichten(Long gebruikerId) {
        return berichtRepository.findByAfzenderId(gebruikerId)
                .stream()
                .map(BerichtMapper::toDTO)
                .collect(Collectors.toList());
    }
}
