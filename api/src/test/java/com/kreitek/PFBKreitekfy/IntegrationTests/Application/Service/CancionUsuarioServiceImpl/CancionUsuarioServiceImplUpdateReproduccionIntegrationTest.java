package com.kreitek.PFBKreitekfy.IntegrationTests.Application.Service.CancionUsuarioServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionUsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionUsuarioMapper;
import com.kreitek.PFBKreitekfy.Application.Service.CancionService;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.CancionUsuarioServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.CancionUsuarioRepository;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl.CancionUsuarioPersistenceImpl;
import com.kreitek.PFBKreitekfy.PfbKreitekfyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class CancionUsuarioServiceImplUpdateReproduccionIntegrationTest {
    @Autowired
    private CancionUsuarioRepository cancionUsuarioRepository;

    @Autowired
    private CancionService cancionService;

    @Autowired
    private CancionUsuarioMapper cancionUsuarioMapper;

    @Autowired
    private CancionMapper cancionMapper;

    @Autowired
    private CancionPersistence cancionPersistence;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldInsertCancionUsuarioWhenUpdateReproduccion() {
        CancionUsuarioServiceImpl cancionUsuarioService = new CancionUsuarioServiceImpl(new CancionUsuarioPersistenceImpl(cancionUsuarioRepository), cancionService, cancionUsuarioMapper, cancionMapper,cancionPersistence);

        CancionUsuarioDTO cancionUsuarioDTO = new CancionUsuarioDTO();
        cancionUsuarioDTO.setUsuarioId(99991L);
        cancionUsuarioDTO.setCancionId(99992L);

        CancionUsuarioDTO cancionUsuarioDTOInserted = cancionUsuarioService.updateReproduccion(cancionUsuarioDTO);

        assertNotNull(cancionUsuarioDTOInserted);
        assertEquals(99991, cancionUsuarioDTOInserted.getUsuarioId());
        assertEquals(99992, cancionUsuarioDTOInserted.getCancionId());
        assertEquals(1, cancionUsuarioDTOInserted.getReproducciones());
    }

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldModifyCancionUsuarioWhenUpdateReproduccion() {
        CancionUsuarioServiceImpl cancionUsuarioService = new CancionUsuarioServiceImpl(new CancionUsuarioPersistenceImpl(cancionUsuarioRepository), cancionService, cancionUsuarioMapper, cancionMapper,cancionPersistence);

        CancionUsuarioDTO cancionUsuarioDTO = new CancionUsuarioDTO();
        cancionUsuarioDTO.setUsuarioId(99991L);
        cancionUsuarioDTO.setCancionId(99991L);

        CancionUsuarioDTO cancionUsuarioDTOModified = cancionUsuarioService.updateReproduccion(cancionUsuarioDTO);

        assertNotNull(cancionUsuarioDTOModified);
        assertEquals(99991, cancionUsuarioDTOModified.getUsuarioId());
        assertEquals(99991, cancionUsuarioDTOModified.getCancionId());
        assertEquals(5, cancionUsuarioDTOModified.getReproducciones());
    }
}
