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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class CancionUsuarioServiceImplGetCancionUsuarioByIdIntegrationTest {
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
    void ShouldReturnNoCancionUsuarioWhenFindOneCancionUsuarioThatIsNot() {
        CancionUsuarioServiceImpl cancionUsuarioService = new CancionUsuarioServiceImpl(new CancionUsuarioPersistenceImpl(cancionUsuarioRepository), cancionService, cancionUsuarioMapper, cancionMapper , cancionPersistence);
        Optional<CancionUsuarioDTO> cancionUsuarioDTO = cancionUsuarioService.getCancionUsuarioById(99990L, 99990L);

        assertNotNull(cancionUsuarioDTO);
        assertFalse(cancionUsuarioDTO.isPresent());
    }

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldReturnCancionUsuarioWhenFindOneCancionUsuario() {
        CancionUsuarioServiceImpl cancionUsuarioService = new CancionUsuarioServiceImpl(new CancionUsuarioPersistenceImpl(cancionUsuarioRepository), cancionService, cancionUsuarioMapper, cancionMapper,cancionPersistence);
        Optional<CancionUsuarioDTO> cancionUsuarioDTO = cancionUsuarioService.getCancionUsuarioById(99991L, 99991L);

        assertNotNull(cancionUsuarioDTO);
        assertTrue(cancionUsuarioDTO.isPresent());
        assertEquals(99991, cancionUsuarioDTO.get().getCancionId());
        assertEquals(99991, cancionUsuarioDTO.get().getUsuarioId());
        assertEquals(3, cancionUsuarioDTO.get().getValoracion());
        assertEquals(4, cancionUsuarioDTO.get().getReproducciones());
    }

}
