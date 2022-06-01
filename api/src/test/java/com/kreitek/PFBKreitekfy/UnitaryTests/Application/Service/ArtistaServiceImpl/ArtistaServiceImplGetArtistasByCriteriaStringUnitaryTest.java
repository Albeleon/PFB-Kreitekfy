package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.ArtistaServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.ArtistaDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.ArtistaMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.ArtistaServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Artista;
import com.kreitek.PFBKreitekfy.Domain.Persistence.ArtistaPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArtistaServiceImplGetArtistasByCriteriaStringUnitaryTest {

    @Test
    void shouldReturnEmptyDTOListWhenRepositoryHasNoartistas() {
        Pageable pageable = PageRequest.of(0, 5);

        ArtistaPersistence mockedArtistaPersistence = mock(ArtistaPersistence.class);
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

        ArtistaPersistence mockedArtistaPersistence = mock(ArtistaPersistence.class);
        when(mockedArtistaPersistence.findAll(pageable, "")).thenReturn(new PageImpl<>(artistas, pageable, 2));
        ArtistaServiceImpl artistaService = new ArtistaServiceImpl(mockedArtistaPersistence, new ArtistaMapperImpl());

        Page<ArtistaDTO> artistaDTOS = artistaService.getArtistasByCriteriaString(pageable, "");

        assertNotNull(artistaDTOS);
        assertNotNull(artistaDTOS.getContent());
        assertEquals(2, artistaDTOS.getTotalElements());
        assertEquals(2, artistaDTOS.getContent().size());
    }
}
