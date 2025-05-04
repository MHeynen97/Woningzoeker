package com.woningzoeker.woningzoeker.mappers;

import com.woningzoeker.woningzoeker.dtos.ContactInfoResponseDTO;
import com.woningzoeker.woningzoeker.models.ContactInfo;

import java.util.List;
import java.util.stream.Collectors;

public class ContactInfoMapper {
    public static ContactInfoResponseDTO toResponseDTO(ContactInfo contactInfo) {
        ContactInfoResponseDTO responseDTO = new ContactInfoResponseDTO();
        responseDTO.setId(contactInfo.getId());
        responseDTO.setEmail(contactInfo.getEmail());
        responseDTO.setProfielId(contactInfo.getProfiel().getId());
        responseDTO.setTelefoonnummer(contactInfo.getTelefoonnummer());

        return responseDTO;
    }

    public static List<ContactInfoResponseDTO> toResponseDTOList(List<ContactInfo> contactData) {
        return contactData.stream().map(ContactInfoMapper::toResponseDTO).collect(Collectors.toList());
    }
}
