package com.kreitek.PFBKreitekfy.Application.Service;

import com.kreitek.PFBKreitekfy.Application.Dto.ArtistaDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.ArtistaDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.ArtistaMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Mapper.ArtistaMapper;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.ArtistaServiceImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.ArtistaServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Artista;
import com.kreitek.PFBKreitekfy.Domain.Entity.Artista;
import com.kreitek.PFBKreitekfy.Domain.Persistence.ArtistaPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArtistaServiceImplTest {
    ArtistaPersistence mockedArtistaPersistence;

    @BeforeEach
    void initialize() {
        mockedArtistaPersistence = mock(ArtistaPersistence.class);
    }

    @Test
    void shouldReturnEmptyDTOListWhenRepositoryHasNoartistas() {
        Pageable pageable = PageRequest.of(0, 5);

        when(mockedArtistaPersistence.findAll(pageable, "")).thenReturn(new PageImpl<>(new ArrayList<>(), pageable, 0));
        ArtistaServiceImpl artistaService = new ArtistaServiceImpl(mockedArtistaPersistence, new ArtistaMapperImpl());

        Page<ArtistaDTO> artistaDTOS = artistaService.getArtistasByCriteriaString(pageable, "");

        assertNotNull(artistaDTOS);
        assertNotNull(artistaDTOS.getContent());
        assertEquals(0, artistaDTOS.getTotalElements());
        assertEquals(0, artistaDTOS.getContent().size());
    }

    @Test
    void shouldReturnTwoDTOListWhenRepositoryHasTwoartistas() {
        Pageable pageable = PageRequest.of(0, 5);

        List<Artista> artistas = new ArrayList<>();
        artistas.add(new Artista());
        artistas.add(new Artista());

        when(mockedArtistaPersistence.findAll(pageable, "")).thenReturn(new PageImpl<>(artistas, pageable, 2));
        ArtistaServiceImpl artistaService = new ArtistaServiceImpl(mockedArtistaPersistence, new ArtistaMapperImpl());

        Page<ArtistaDTO> artistaDTOS = artistaService.getArtistasByCriteriaString(pageable, "");

        assertNotNull(artistaDTOS);
        assertNotNull(artistaDTOS.getContent());
        assertEquals(2, artistaDTOS.getTotalElements());
        assertEquals(2, artistaDTOS.getContent().size());
    }

    @Test
    void shouldReturnEmptyDTOartistaWhenRepositoryHasNotThatartista() {
        when(mockedArtistaPersistence.getArtistaById(1L)).thenReturn(Optional.empty());
        ArtistaServiceImpl artistaService = new ArtistaServiceImpl(mockedArtistaPersistence, new ArtistaMapperImpl());

        Optional<ArtistaDTO> artistaDTO = artistaService.getArtistaById(1L);

        assertNotNull(artistaDTO);
        assertFalse(artistaDTO.isPresent());
    }

    @Test
    void shouldReturnDTOartistaWhenRepositoryHasThatartista() {
        when(mockedArtistaPersistence.getArtistaById(1L)).thenReturn(Optional.of(new Artista()));
        ArtistaServiceImpl artistaService = new ArtistaServiceImpl(mockedArtistaPersistence, new ArtistaMapperImpl());

        Optional<ArtistaDTO> artistaDTO = artistaService.getArtistaById(1L);

        assertNotNull(artistaDTO);
        assertTrue(artistaDTO.isPresent());
    }

    @Test
    void shouldReturnDTOArtistaWhenRepositorySavesArtista() {

        ArtistaDTO artistaDTO = new ArtistaDTO();
        ArtistaDTO artistaDTOUpdated = new ArtistaDTO();

        Artista artista = new Artista();
        Artista artistaUpdated = new Artista();

        ArtistaMapper mockedArtistaMapper = mock(ArtistaMapper.class);
        when(mockedArtistaMapper.toEntity(artistaDTO)).thenReturn(artista);
        when(mockedArtistaMapper.toDto(artistaUpdated)).thenReturn(artistaDTOUpdated);

        when(mockedArtistaPersistence.saveArtista(artista)).thenReturn(artistaUpdated);

        ArtistaServiceImpl artistaService = new ArtistaServiceImpl(mockedArtistaPersistence, mockedArtistaMapper);

        ArtistaDTO artistaUpdatedDTO = artistaService.saveArtista(artistaDTO);

        assertNotNull(artistaUpdatedDTO);
    }
}