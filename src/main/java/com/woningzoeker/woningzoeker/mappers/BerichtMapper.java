package com.woningzoeker.woningzoeker.mappers;

import com.woningzoeker.woningzoeker.dtos.BerichtResponseDTO;
import com.woningzoeker.woningzoeker.dtos.BiedingResponseDTO;
import com.woningzoeker.woningzoeker.models.Bericht;
import com.woningzoeker.woningzoeker.models.Bieding;

import java.util.List;
import java.util.stream.Collectors;

public class BerichtMapper {

    public static BerichtResponseDTO toResponseDTO(Bericht bericht) {
        BerichtResponseDTO dto = new BerichtResponseDTO();
        dto.setId(bericht.getId());
        dto.setOnderwerp(bericht.getOnderwerp());
        dto.setInhoud(bericht.getInhoud());
        dto.setVerzondenOp(bericht.getVerzondenOp());
        dto.setAfzenderId(bericht.getAfzender().getId());
        dto.setOntvangerId(bericht.getOntvanger().getId());
        return dto;
    }
}
