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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class ArtistaServiceImplGetArtistasByCriteriaStringIntegrationTest {
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
}
