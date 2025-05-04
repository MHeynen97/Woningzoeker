package com.woningzoeker.woningzoeker.mappers;

import com.woningzoeker.woningzoeker.dtos.BiedingResponseDTO;

import com.woningzoeker.woningzoeker.models.Bieding;

import java.util.List;
import java.util.stream.Collectors;

public class BiedingMapper {
    // Converteer een Bieding model naar een BiedingResponseDTO
    public static BiedingResponseDTO toResponseDTO(Bieding bieding) {
        BiedingResponseDTO responseDto = new BiedingResponseDTO();
        responseDto.setId(bieding.getId());
        responseDto.setBieder(bieding.getBieder().getId());
        responseDto.setBod(bieding.getBod());
        responseDto.setEigenaar(bieding.getEigenaar().getId());
        responseDto.setPrijs(bieding.getPrijs());
        responseDto.setEindDatum(bieding.getEindDatum());
        responseDto.setHuisId(bieding.getHuis().getId());

        // Haal het huisId uit het huis object
        if (bieding.getHuis() != null) {
            responseDto.setHuisId(bieding.getHuis().getId());
        }

        return responseDto;
    }

    // Converteer een lijst van Bieding modellen naar een lijst van BiedingResponseDTO's
    public static List<BiedingResponseDTO> toResponseDTOList(List<Bieding> biedingen) {
        return biedingen.stream().map(BiedingMapper::toResponseDTO).collect(Collectors.toList());
    }
}
