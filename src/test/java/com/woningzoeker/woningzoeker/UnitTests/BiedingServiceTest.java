package com.woningzoeker.woningzoeker.UnitTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.woningzoeker.woningzoeker.models.Bieding;
import com.woningzoeker.woningzoeker.repositories.BiedingRepository;
import com.woningzoeker.woningzoeker.services.BiedingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BiedingServiceTest {

    @Mock
    private BiedingRepository biedingRepository;

    @InjectMocks
    private BiedingService biedingService;

    private Bieding bieding;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bieding = new Bieding();
        bieding.setId(1L);
    }

    @Test
    public void testSave() {
        // Arrange
        when(biedingRepository.save(bieding)).thenReturn(bieding);

        // Act
        Bieding saved = biedingService.save(bieding);

        // Assert
        assertNotNull(saved);
        assertEquals(1L, saved.getId());
        verify(biedingRepository, times(1)).save(bieding);
    }

    @Test
    public void testSaveAll() {
        // Arrange
        List<Bieding> biedingen = List.of(bieding);
        when(biedingRepository.saveAll(biedingen)).thenReturn(biedingen);

        // Act
        List<Bieding> result = biedingService.saveAll(biedingen);

        // Assert
        assertEquals(1, result.size());
        verify(biedingRepository).saveAll(biedingen);
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<Bieding> biedingen = List.of(bieding);
        when(biedingRepository.findAll()).thenReturn(biedingen);

        // Act
        List<Bieding> result = biedingService.findAll();

        // Assert
        assertEquals(1, result.size());
        verify(biedingRepository).findAll();
    }

    @Test
    public void testFindByIdFound() {
        // Arrange
        when(biedingRepository.findById(1L)).thenReturn(Optional.of(bieding));

        // Act
        Optional<Bieding> result = biedingService.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(biedingRepository).findById(1L);
    }

    @Test
    public void testFindByIdNotFound() {
        // Arrange
        when(biedingRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Optional<Bieding> result = biedingService.findById(99L);

        // Assert
        assertFalse(result.isPresent());
        verify(biedingRepository).findById(99L);
    }

    @Test
    public void testDeleteExists() {
        // Arrange
        when(biedingRepository.existsById(1L)).thenReturn(true);

        // Act
        boolean deleted = biedingService.delete(1L);

        // Assert
        assertTrue(deleted);
        verify(biedingRepository).deleteById(1L);
    }

    @Test
    public void testDeleteNotExists() {
        // Arrange
        when(biedingRepository.existsById(1L)).thenReturn(false);

        // Act
        boolean deleted = biedingService.delete(1L);

        // Assert
        assertFalse(deleted);
        verify(biedingRepository, never()).deleteById(anyLong());
    }
}
