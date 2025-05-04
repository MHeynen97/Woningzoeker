package com.woningzoeker.woningzoeker.mappers;

import com.woningzoeker.woningzoeker.dtos.ProfielResponseDTO;
import com.woningzoeker.woningzoeker.models.Profiel;

import java.util.List;
import java.util.stream.Collectors;

public class ProfielMapper {
    public static ProfielResponseDTO toResponseDTO(Profiel profiel) {
        ProfielResponseDTO responseDTO = new ProfielResponseDTO();
        responseDTO.setId(profiel.getId());
        responseDTO.setNaam(profiel.getNaam());
        responseDTO.setGeboortedatum(profiel.getGeboortedatum());
        responseDTO.setOmschrijving(profiel.getOmschrijving());

        // Haal gebruikersnaam op uit gekoppelde gebruiker
        if (profiel.getGebruiker() != null) {
            responseDTO.setGebruikersnaam(profiel.getGebruiker().getGebruikersnaam());
        }
        responseDTO.setFavorieteHuizen(profiel.getFavorieteHuizen());

        responseDTO.setOmschrijving(profiel.getOmschrijving());

        // Haal email en telefoonnummer op uit contactInfo
        if (profiel.getContactInfo() != null) {
            responseDTO.setEmail(profiel.getContactInfo().getEmail());
            responseDTO.setTelefoonnummer(profiel.getContactInfo().getTelefoonnummer());
        }

        // Haal woonplaats op uit locatie
        if (profiel.getLocatie() != null) {
            responseDTO.setWoonplaats(profiel.getLocatie().getWoonplaats());
        }

        return responseDTO;
    }

    public static List<ProfielResponseDTO> toResponseDTOList(List<Profiel> profielen) {
        return profielen.stream().map(ProfielMapper::toResponseDTO).collect(Collectors.toList());
    }
}
