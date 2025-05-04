package com.woningzoeker.woningzoeker.mappers;

import com.woningzoeker.woningzoeker.dtos.BerichtResponseDTO;
import com.woningzoeker.woningzoeker.models.Bericht;

public class BerichtMapper {

    public static BerichtResponseDTO toDTO(Bericht bericht) {
        BerichtResponseDTO dto = new BerichtResponseDTO();
        dto.setId(bericht.getId());
        dto.setOnderwerp(bericht.getOnderwerp());
        dto.setInhoud(bericht.getInhoud());
        dto.setVerzondenOp(bericht.getVerzondenOp());
        dto.setAfzenderId(bericht.getAfzender().getId());
        dto.setOntvangerId(bericht.getOntvanger().getId());
        return dto;
    }

    public static Bericht toEntity(BerichtResponseDTO dto) {
        Bericht bericht = new Bericht();
        bericht.setId(dto.getId());
        bericht.setOnderwerp(dto.getOnderwerp());
        bericht.setInhoud(dto.getInhoud());
        bericht.setVerzondenOp(dto.getVerzondenOp());
        // Let op: afzender/ontvanger moet apart gezet worden via service/repository
        return bericht;
    }
}
