package com.woningzoeker.woningzoeker.mappers;

import com.woningzoeker.woningzoeker.dtos.GebruikerResponseDto;
import com.woningzoeker.woningzoeker.models.Gebruiker;

import java.util.List;
import java.util.stream.Collectors;

public class GebruikerMapper {
    public static GebruikerResponseDto toResponseDTO(Gebruiker gebruiker) {
        var responseDto = new GebruikerResponseDto();
        responseDto.setId(gebruiker.getId());
        responseDto.setGebruikersnaam(gebruiker.getGebruikersnaam());
        responseDto.setEmail(gebruiker.getEmail());

        return responseDto;
    }

    public static List<GebruikerResponseDto> toResponseDTOList(List<Gebruiker> gebruikers) {
        return gebruikers.stream().map(GebruikerMapper::toResponseDTO).collect(Collectors.toList());
    }
}
