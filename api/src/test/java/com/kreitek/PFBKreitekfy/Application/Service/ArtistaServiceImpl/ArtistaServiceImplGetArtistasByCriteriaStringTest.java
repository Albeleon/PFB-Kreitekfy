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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class ArtistaServiceImplGetArtistasByCriteriaStringTest {
    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private ArtistaMapper artistaMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldReturnArtistaWhenFindAllArtistaes() {
        ArtistaServiceImpl artistaService = new ArtistaServiceImpl(new ArtistaPersistenceImpl(artistaRepository), artistaMapper);
        Pageable pageable = PageRequest.of(0, 5);
        Page<ArtistaDTO> artistaDTO = artistaService.getArtistasByCriteriaString(pageable, "");

        assertNotNull(artistaDTO);
        assertEquals(1, artistaDTO.getContent().size());
        assertEquals(99991, artistaDTO.getContent().get(0).getId());
        assertEquals("_-_artista_-_", artistaDTO.getContent().get(0).getNombre());
    }

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
