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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class ArtistaServiceImplDeleteArtistaByIdIntegrationTest {
    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private ArtistaMapper artistaMapper;
        @Sql({"/test_data.sql"})
        @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
        @Test
        void ShouldDeleteArtistaWhenDeleteItem() {
            ArtistaServiceImpl artistaService = new ArtistaServiceImpl(new ArtistaPersistenceImpl(artistaRepository), artistaMapper);

            artistaService.deleteArtistaById(99991L);
            Optional<ArtistaDTO> artistaDTODeleted = artistaService.getArtistaById(99991L);

            assertNotNull(artistaDTODeleted);
            assertFalse(artistaDTODeleted.isPresent());
        }
}
