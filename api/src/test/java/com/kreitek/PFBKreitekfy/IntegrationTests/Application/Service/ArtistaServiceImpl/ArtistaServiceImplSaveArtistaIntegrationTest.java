package com.kreitek.PFBKreitekfy.IntegrationTests.Application.Service.ArtistaServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.ArtistaDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.ArtistaMapper;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.ArtistaServiceImpl;
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

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class ArtistaServiceImplSaveArtistaIntegrationTest {
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
}
