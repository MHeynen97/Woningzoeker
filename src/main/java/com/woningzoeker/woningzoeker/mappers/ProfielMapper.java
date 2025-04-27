package com.woningzoeker.woningzoeker.mappers;

import com.woningzoeker.woningzoeker.dtos.LocatieResponseDTO;
import com.woningzoeker.woningzoeker.dtos.ProfielResponseDTO;
import com.woningzoeker.woningzoeker.models.Locatie;
import com.woningzoeker.woningzoeker.models.Profiel;

import java.util.List;
import java.util.stream.Collectors;

public class ProfielMapper {
    public static ProfielResponseDTO toResponseDTO(Profiel profiel) {
        ProfielResponseDTO responseDTO = new ProfielResponseDTO();
        responseDTO.setId(profiel.getId());
        responseDTO.setEmail(profiel.getEmail());
        responseDTO.setGeboortedatum(profiel.getGeboortedatum());
        responseDTO.setGebruikersnaam(profiel.getGebruikersnaam());
        responseDTO.setNaam(profiel.getNaam());
        responseDTO.setFavorieteHuizen(profiel.getFavorieteHuizen());
        responseDTO.setInkomendeBerichten(profiel.getInkomendeBerichten());
        responseDTO.setUitgaandeBerichten(profiel.getUitgaandeBerichten());
        responseDTO.setOmschrijving(profiel.getOmschrijving());
        responseDTO.setTelefoonnummer(profiel.getTelefoonnummer());
        responseDTO.setWoonplaats(profiel.getWoonplaats());

        return responseDTO;
    }

    public static List<ProfielResponseDTO> toResponseDTOList(List<Profiel> profielen) {
        return profielen.stream().map(ProfielMapper::toResponseDTO).collect(Collectors.toList());
    }
}
