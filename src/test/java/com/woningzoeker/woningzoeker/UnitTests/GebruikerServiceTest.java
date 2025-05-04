package com.woningzoeker.woningzoeker.UnitTests;

import com.woningzoeker.woningzoeker.models.Gebruiker;
import com.woningzoeker.woningzoeker.repositories.GebruikerRepository;
import com.woningzoeker.woningzoeker.services.GebruikerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setGebruikersnaam("test");

        when(gebruikerRepository.save(gebruiker)).thenReturn(gebruiker);

        Gebruiker saved = gebruikerService.save(gebruiker);
        assertEquals("test", saved.getGebruikersnaam());
        verify(gebruikerRepository, times(1)).save(gebruiker);
    }

    @Test
    void testSaveAll() {
        List<Gebruiker> gebruikers = Arrays.asList(new Gebruiker(), new Gebruiker());
        when(gebruikerRepository.saveAll(gebruikers)).thenReturn(gebruikers);

        List<Gebruiker> result = gebruikerService.saveAll(gebruikers);
        assertEquals(2, result.size());
        verify(gebruikerRepository).saveAll(gebruikers);
    }

    @Test
    void testFindAll() {
        when(gebruikerRepository.findAll()).thenReturn(List.of(new Gebruiker()));
        List<Gebruiker> result = gebruikerService.findAll();
        assertFalse(result.isEmpty());
    }

    @Test
    void testFindById() {
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setId(1L);

        when(gebruikerRepository.findById(1L)).thenReturn(Optional.of(gebruiker));

        Optional<Gebruiker> result = gebruikerService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testFindByGebruikersnaam() {
        when(gebruikerRepository.findByGebruikersnaam("john")).thenReturn(List.of(new Gebruiker()));

        List<Gebruiker> result = gebruikerService.findByGebruikersnaam("john");
        assertEquals(1, result.size());
    }

    @Test
    void testDelete_Success() {
        when(gebruikerRepository.existsById(1L)).thenReturn(true);
        boolean result = gebruikerService.delete(1L);
        assertTrue(result);
        verify(gebruikerRepository).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        when(gebruikerRepository.existsById(2L)).thenReturn(false);
        boolean result = gebruikerService.delete(2L);
        assertFalse(result);
        verify(gebruikerRepository, never()).deleteById(any());
    }

    @Test
    void testGetGebruikers_WithGebruikersnaam() {
        when(gebruikerRepository.findByGebruikersnaam("alice")).thenReturn(List.of(new Gebruiker()));
        List<Gebruiker> result = gebruikerService.getGebruikers("alice");
        assertEquals(1, result.size());
    }

    @Test
    void testGetGebruikers_WithoutGebruikersnaam() {
        when(gebruikerRepository.findAll()).thenReturn(List.of(new Gebruiker(), new Gebruiker()));
        List<Gebruiker> result = gebruikerService.getGebruikers(null);
        assertEquals(2, result.size());
    }
}
