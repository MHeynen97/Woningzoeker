package com.woningzoeker.woningzoeker.mappers;

import com.woningzoeker.woningzoeker.dtos.BiedingResponseDTO;

import com.woningzoeker.woningzoeker.models.Bieding;

import java.util.List;
import java.util.stream.Collectors;

public class BiedingMapper {
    public static BiedingResponseDTO toResponseDTO(Bieding bieding) {
        BiedingResponseDTO responseDto = new BiedingResponseDTO();
        responseDto.setId(bieding.getId());
        responseDto.setBieder(bieding.getBieder());
        responseDto.setBod(bieding.getBod());
        responseDto.setEigenaar(bieding.getEigenaar());
        responseDto.setPrijs(bieding.getPrijs());
        responseDto.setEindDatum(bieding.getEindDatum());
        responseDto.setHuisId(bieding.getHuisId());

        return responseDto;
    }

    public static List<BiedingResponseDTO> toResponseDTOList(List<Bieding> biedingen) {
        return biedingen.stream().map(BiedingMapper::toResponseDTO).collect(Collectors.toList());
    }
}
