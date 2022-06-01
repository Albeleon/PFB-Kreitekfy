package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.ArtistaServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.ArtistaDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.ArtistaMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.ArtistaServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Artista;
import com.kreitek.PFBKreitekfy.Domain.Persistence.ArtistaPersistence;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArtistaServiceImplGetArtistaByIdUnitaryTest {

    @Test
    void shouldReturnEmptyDTOartistaWhenRepositoryHasNotThatartista() {
        ArtistaPersistence mockedArtistaPersistence = mock(ArtistaPersistence.class);
        when(mockedArtistaPersistence.getArtistaById(1L)).thenReturn(Optional.empty());
        ArtistaServiceImpl artistaService = new ArtistaServiceImpl(mockedArtistaPersistence, new ArtistaMapperImpl());

        Optional<ArtistaDTO> artistaDTO = artistaService.getArtistaById(1L);

        assertNotNull(artistaDTO);
        assertFalse(artistaDTO.isPresent());
    }

    @Test
    void shouldReturnDTOartistaWhenRepositoryHasThatartista() {
        ArtistaPersistence mockedArtistaPersistence = mock(ArtistaPersistence.class);
        when(mockedArtistaPersistence.getArtistaById(1L)).thenReturn(Optional.of(new Artista()));
        ArtistaServiceImpl artistaService = new ArtistaServiceImpl(mockedArtistaPersistence, new ArtistaMapperImpl());

        Optional<ArtistaDTO> artistaDTO = artistaService.getArtistaById(1L);

        assertNotNull(artistaDTO);
        assertTrue(artistaDTO.isPresent());
    }
}
