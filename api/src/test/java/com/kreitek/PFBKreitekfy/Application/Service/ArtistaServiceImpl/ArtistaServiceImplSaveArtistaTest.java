package com.kreitek.PFBKreitekfy.Application.Service.ArtistaServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.ArtistaDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.ArtistaMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class ArtistaServiceImplSaveArtistaTest {
    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private ArtistaMapper artistaMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldInsertArtistaWhenSaveItem() {
        ArtistaServiceImpl artistaService = new ArtistaServiceImpl(new ArtistaPersistenceImpl(artistaRepository), artistaMapper);

        ArtistaDTO artistaDTO = new ArtistaDTO();
        artistaDTO.setNombre("Nuevo artista");
        ArtistaDTO artistaDTOInserted = artistaService.saveArtista(artistaDTO);

        assertNotNull(artistaDTOInserted);
        assertEquals("Nuevo artista", artistaDTOInserted.getNombre());
    }

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldModifyArtistaWhenSaveItem() {
        ArtistaServiceImpl artistaService = new ArtistaServiceImpl(new ArtistaPersistenceImpl(artistaRepository), artistaMapper);

        ArtistaDTO artistaDTO = new ArtistaDTO();
        artistaDTO.setId(99991L);
        artistaDTO.setNombre("Artista modificado");
        ArtistaDTO artistaDTOModified = artistaService.saveArtista(artistaDTO);

        assertNotNull(artistaDTOModified);
        assertEquals("Artista modificado", artistaDTOModified.getNombre());
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

        ArtistaPersistence mockedArtistaPersistence = mock(ArtistaPersistence.class);
        when(mockedArtistaPersistence.saveArtista(artista)).thenReturn(artistaUpdated);

        ArtistaServiceImpl artistaService = new ArtistaServiceImpl(mockedArtistaPersistence, mockedArtistaMapper);

        ArtistaDTO artistaUpdatedDTO = artistaService.saveArtista(artistaDTO);

        assertNotNull(artistaUpdatedDTO);
    }
}
