package com.kreitek.PFBKreitekfy.Application.Service.ArtistaServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.ArtistaDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.ArtistaMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.ArtistaMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.ArtistaServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Artista;
import com.kreitek.PFBKreitekfy.Domain.Persistence.ArtistaPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.ArtistaRepository;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl.ArtistaPersistenceImpl;
import com.kreitek.PFBKreitekfy.PfbKreitekfyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class ArtistaServiceImplGetArtistaByIdTest {
    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private ArtistaMapper artistaMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldReturnArtistaWhenFindOneArtista() {
        ArtistaServiceImpl artistaService = new ArtistaServiceImpl(new ArtistaPersistenceImpl(artistaRepository), artistaMapper);
        Optional<ArtistaDTO> artistaDTO = artistaService.getArtistaById(99991L);

        assertNotNull(artistaDTO);
        assertTrue(artistaDTO.isPresent());
        assertEquals(99991, artistaDTO.get().getId());
        assertEquals("_-_artista_-_", artistaDTO.get().getNombre());
    }

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldReturnNoArtistaWhenFindOneArtistaThatIsNot() {
        ArtistaServiceImpl artistaService = new ArtistaServiceImpl(new ArtistaPersistenceImpl(artistaRepository), artistaMapper);
        Optional<ArtistaDTO> artistaDTO = artistaService.getArtistaById(99990L);

        assertNotNull(artistaDTO);
        assertFalse(artistaDTO.isPresent());
    }

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
