package com.woningzoeker.woningzoeker.UnitTests;

import com.woningzoeker.woningzoeker.models.Gebruiker;
import com.woningzoeker.woningzoeker.repositories.GebruikerRepository;
import com.woningzoeker.woningzoeker.services.GebruikerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    void testSaveGebruiker() {
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setId(1L);
        gebruiker.setGebruikersnaam("testgebruiker");

        when(gebruikerRepository.save(gebruiker)).thenReturn(gebruiker);

        Gebruiker result = gebruikerService.save(gebruiker);

        assertThat(result).isEqualTo(gebruiker);
        verify(gebruikerRepository).save(gebruiker);
    }

    @Test
    void testSaveAllGebruikers() {
        Gebruiker g1 = new Gebruiker();
        Gebruiker g2 = new Gebruiker();
        List<Gebruiker> gebruikers = Arrays.asList(g1, g2);

        when(gebruikerRepository.saveAll(gebruikers)).thenReturn(gebruikers);

        List<Gebruiker> result = gebruikerService.saveAll(gebruikers);

        assertThat(result).hasSize(2);
        verify(gebruikerRepository).saveAll(gebruikers);
    }

    @Test
    void testFindAll() {
        List<Gebruiker> gebruikers = List.of(new Gebruiker(), new Gebruiker());
        when(gebruikerRepository.findAll()).thenReturn(gebruikers);

        List<Gebruiker> result = gebruikerService.findAll();

        assertThat(result).hasSize(2);
        verify(gebruikerRepository).findAll();
    }

    @Test
    void testFindById() {
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setId(1L);

        when(gebruikerRepository.findById(1L)).thenReturn(Optional.of(gebruiker));

        Optional<Gebruiker> result = gebruikerService.findById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1L);
        verify(gebruikerRepository).findById(1L);
    }

    @Test
    void testFindByGebruikersnaam() {
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setGebruikersnaam("testgebruiker");

        when(gebruikerRepository.findByGebruikersnaam("testgebruiker")).thenReturn(List.of(gebruiker));

        List<Gebruiker> result = gebruikerService.findByGebruikersnaam("testgebruiker");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getGebruikersnaam()).isEqualTo("testgebruiker");
        verify(gebruikerRepository).findByGebruikersnaam("testgebruiker");
    }

    @Test
    void testDeleteSuccess() {
        when(gebruikerRepository.existsById(1L)).thenReturn(true);

        boolean result = gebruikerService.delete(1L);

        assertThat(result).isTrue();
        verify(gebruikerRepository).deleteById(1L);
    }

    @Test
    void testDeleteFailure() {
        when(gebruikerRepository.existsById(1L)).thenReturn(false);

        boolean result = gebruikerService.delete(1L);

        assertThat(result).isFalse();
        verify(gebruikerRepository, never()).deleteById(anyLong());
    }

    @Test
    void testGetGebruikersWithGebruikersnaam() {
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setGebruikersnaam("gebruiker1");

        when(gebruikerRepository.findByGebruikersnaam("gebruiker1")).thenReturn(List.of(gebruiker));

        List<Gebruiker> result = gebruikerService.getGebruikers("gebruiker1");

        assertThat(result).hasSize(1);
        verify(gebruikerRepository).findByGebruikersnaam("gebruiker1");
    }

    @Test
    void testGetGebruikersWithoutGebruikersnaam() {
        List<Gebruiker> gebruikers = List.of(new Gebruiker(), new Gebruiker());
        when(gebruikerRepository.findAll()).thenReturn(gebruikers);

        List<Gebruiker> result = gebruikerService.getGebruikers(null);

        assertThat(result).hasSize(2);
        verify(gebruikerRepository).findAll();
    }
}