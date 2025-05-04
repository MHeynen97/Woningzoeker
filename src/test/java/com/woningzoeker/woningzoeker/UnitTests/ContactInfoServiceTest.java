package com.woningzoeker.woningzoeker.UnitTests;

import com.woningzoeker.woningzoeker.models.ContactInfo;
import com.woningzoeker.woningzoeker.models.Profiel;
import com.woningzoeker.woningzoeker.repositories.ContactInfoRepository;
import com.woningzoeker.woningzoeker.services.ContactInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactInfoServiceTest {

    private ContactInfoRepository contactInfoRepository;
    private ContactInfoService contactInfoService;

    @BeforeEach
    void setUp() {
        contactInfoRepository = mock(ContactInfoRepository.class);
        contactInfoService = new ContactInfoService(contactInfoRepository);
    }

    @Test
    void testSave() {
        // Arrange
        Profiel profiel = new Profiel();
        profiel.setId(1L);
        profiel.setNaam("Test Profiel");

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setId(1L);
        contactInfo.setProfiel(profiel);
        contactInfo.setEmail("test@example.com");
        contactInfo.setTelefoonnummer("123456789");

        when(contactInfoRepository.save(contactInfo)).thenReturn(contactInfo);

        // Act
        ContactInfo savedContactInfo = contactInfoService.save(contactInfo);

        // Assert
        assertNotNull(savedContactInfo);
        assertEquals(1L, savedContactInfo.getId());
        assertEquals("test@example.com", savedContactInfo.getEmail());
        assertEquals("123456789", savedContactInfo.getTelefoonnummer());
        verify(contactInfoRepository).save(contactInfo);
    }

    @Test
    void testSaveAll() {
        // Arrange
        Profiel profiel1 = new Profiel();
        profiel1.setId(1L);
        profiel1.setNaam("Test Profiel 1");

        Profiel profiel2 = new Profiel();
        profiel2.setId(2L);
        profiel2.setNaam("Test Profiel 2");

        ContactInfo contactInfo1 = new ContactInfo(profiel1, "test1@example.com", "123456789");
        ContactInfo contactInfo2 = new ContactInfo(profiel2, "test2@example.com", "987654321");

        List<ContactInfo> contactInfoList = Arrays.asList(contactInfo1, contactInfo2);

        when(contactInfoRepository.saveAll(contactInfoList)).thenReturn(contactInfoList);

        // Act
        List<ContactInfo> savedContactInfos = contactInfoService.saveAll(contactInfoList);

        // Assert
        assertEquals(2, savedContactInfos.size());
        assertEquals("test1@example.com", savedContactInfos.get(0).getEmail());
        assertEquals("test2@example.com", savedContactInfos.get(1).getEmail());
        verify(contactInfoRepository).saveAll(contactInfoList);
    }

    @Test
    void testFindAll() {
        // Arrange
        Profiel profiel = new Profiel();
        profiel.setId(1L);
        profiel.setNaam("Test Profiel");

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setId(1L);
        contactInfo.setProfiel(profiel);
        contactInfo.setEmail("test@example.com");
        contactInfo.setTelefoonnummer("123456789");

        when(contactInfoRepository.findAll()).thenReturn(Arrays.asList(contactInfo));

        // Act
        List<ContactInfo> contactInfos = contactInfoService.findAll();

        // Assert
        assertFalse(contactInfos.isEmpty());
        assertEquals(1L, contactInfos.get(0).getId());
        assertEquals("test@example.com", contactInfos.get(0).getEmail());
    }

    @Test
    void testFindById() {
        // Arrange
        Profiel profiel = new Profiel();
        profiel.setId(1L);
        profiel.setNaam("Test Profiel");

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setId(1L);
        contactInfo.setProfiel(profiel);
        contactInfo.setEmail("test@example.com");
        contactInfo.setTelefoonnummer("123456789");

        when(contactInfoRepository.findById(1L)).thenReturn(Optional.of(contactInfo));

        // Act
        Optional<ContactInfo> result = contactInfoService.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
    }

    @Test
    void testDelete_Success() {
        // Arrange
        when(contactInfoRepository.existsById(1L)).thenReturn(true);

        // Act
        boolean result = contactInfoService.delete(1L);

        // Assert
        assertTrue(result);
        verify(contactInfoRepository).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        // Arrange
        when(contactInfoRepository.existsById(2L)).thenReturn(false);

        // Act
        boolean result = contactInfoService.delete(2L);

        // Assert
        assertFalse(result);
        verify(contactInfoRepository, never()).deleteById(any());
    }
}
