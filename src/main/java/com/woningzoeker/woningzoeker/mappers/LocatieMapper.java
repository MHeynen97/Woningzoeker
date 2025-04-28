package com.woningzoeker.woningzoeker.mappers;

import com.woningzoeker.woningzoeker.dtos.LocatieResponseDTO;
import com.woningzoeker.woningzoeker.models.Locatie;
import java.util.List;
import java.util.stream.Collectors;

public class LocatieMapper {
    public static LocatieResponseDTO toResponseDTO(Locatie locatie) {
        LocatieResponseDTO responseDTO = new LocatieResponseDTO();
        responseDTO.setId(locatie.getId());
        responseDTO.setAdres(locatie.getAdres());
        responseDTO.setPostcode(locatie.getPostcode());
        responseDTO.setWoonplaats(locatie.getWoonplaats());

        return responseDTO;
    }

    public static List<LocatieResponseDTO> toResponseDTOList(List<Locatie> locaties) {
        return locaties.stream().map(LocatieMapper::toResponseDTO).collect(Collectors.toList());
    }
}
