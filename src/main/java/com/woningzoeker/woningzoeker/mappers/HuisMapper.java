package com.woningzoeker.woningzoeker.mappers;

import com.woningzoeker.woningzoeker.dtos.HuisResponseDTO;
import com.woningzoeker.woningzoeker.models.Huis;
import com.woningzoeker.woningzoeker.models.HuisFoto;

import java.util.List;
import java.util.stream.Collectors;

public class HuisMapper {
    public static HuisResponseDTO toResponseDTO(Huis huis) {
        HuisResponseDTO responseDTO = new HuisResponseDTO();
        responseDTO.setId(huis.getId());
        responseDTO.setAantalKamers(huis.getAantalKamers());
        responseDTO.setAdres(huis.getLocatie().getAdres());
        responseDTO.setFotos(
                huis.getHuisFotos()
                        .stream()
                        .map(HuisFoto::getFileName)
                        .toList()
        );
        responseDTO.setPrijs(huis.getPrijs());
        responseDTO.setEnergieLabel(huis.getEnergieLabel());
        responseDTO.setOmschrijving(huis.getOmschrijving());
        responseDTO.setHuurkoop(huis.getHuurkoop());

        return responseDTO;
    }

    public static List<HuisResponseDTO> toResponseDTOList(List<Huis> huizen) {
        return huizen.stream().map(HuisMapper::toResponseDTO).collect(Collectors.toList());
    }
}
