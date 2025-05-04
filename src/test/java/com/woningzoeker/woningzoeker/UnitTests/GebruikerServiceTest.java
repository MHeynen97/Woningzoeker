package com.woningzoeker.woningzoeker.UnitTests;

import com.woningzoeker.woningzoeker.models.Gebruiker;
import com.woningzoeker.woningzoeker.repositories.GebruikerRepository;
import com.woningzoeker.woningzoeker.services.GebruikerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GebruikerServiceTest {

    private GebruikerRepository gebruikerRepository;
    private GebruikerService gebruikerService;

    @BeforeEach
    void setUp() {
        gebruikerRepository = mock(GebruikerRepository.class);
        gebruikerService = new GebruikerService(gebruikerRepository);
    }

    @Test
    void testSave() {
        // Arrange
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setGebruikersnaam("test");
        when(gebruikerRepository.save(gebruiker)).thenReturn(gebruiker);

        // Act
        Gebruiker saved = gebruikerService.save(gebruiker);

        // Assert
        assertEquals("test", saved.getGebruikersnaam());
        verify(gebruikerRepository, times(1)).save(gebruiker);
    }

    @Test
    void testSaveAll() {
        // Arrange
        List<Gebruiker> gebruikers = Arrays.asList(new Gebruiker(), new Gebruiker());
        when(gebruikerRepository.saveAll(gebruikers)).thenReturn(gebruikers);

        // Act
        List<Gebruiker> result = gebruikerService.saveAll(gebruikers);

        // Assert
        assertEquals(2, result.size());
        verify(gebruikerRepository).saveAll(gebruikers);
    }

    @Test
    void testFindAll() {
        // Arrange
        when(gebruikerRepository.findAll()).thenReturn(List.of(new Gebruiker()));

        // Act
        List<Gebruiker> result = gebruikerService.findAll();

        // Assert
        assertFalse(result.isEmpty());
    }

    @Test
    void testFindById() {
        // Arrange
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setId(1L);
        when(gebruikerRepository.findById(1L)).thenReturn(Optional.of(gebruiker));

        // Act
        Optional<Gebruiker> result = gebruikerService.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testFindByGebruikersnaam() {
        // Arrange
        when(gebruikerRepository.findByGebruikersnaam("john")).thenReturn(List.of(new Gebruiker()));

        // Act
        List<Gebruiker> result = gebruikerService.findByGebruikersnaam("john");

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void testDelete_Success() {
        // Arrange
        when(gebruikerRepository.existsById(1L)).thenReturn(true);

        // Act
        boolean result = gebruikerService.delete(1L);

        // Assert
        assertTrue(result);
        verify(gebruikerRepository).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        // Arrange
        when(gebruikerRepository.existsById(2L)).thenReturn(false);

        // Act
        boolean result = gebruikerService.delete(2L);

        // Assert
        assertFalse(result);
        verify(gebruikerRepository, never()).deleteById(any());
    }

    @Test
    void testGetGebruikers_WithGebruikersnaam() {
        // Arrange
        when(gebruikerRepository.findByGebruikersnaam("alice")).thenReturn(List.of(new Gebruiker()));

        // Act
        List<Gebruiker> result = gebruikerService.getGebruikers("alice");

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void testGetGebruikers_WithoutGebruikersnaam() {
        // Arrange
        when(gebruikerRepository.findAll()).thenReturn(List.of(new Gebruiker(), new Gebruiker()));

        // Act
        List<Gebruiker> result = gebruikerService.getGebruikers(null);

        // Assert
        assertEquals(2, result.size());
    }
}
