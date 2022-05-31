package com.kreitek.PFBKreitekfy.Application.Service.CancionUsuarioServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionUsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionUsuarioMapper;
import com.kreitek.PFBKreitekfy.Application.Service.CancionService;
import com.kreitek.PFBKreitekfy.Application.Service.CancionUsuarioService;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.CancionUsuarioServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionUsuarioPersistence;
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
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class CancionUsuarioServiceImplUpdateReproduccionTest {
    @Autowired
    private CancionUsuarioRepository cancionUsuarioRepository;

    @Autowired
    private CancionService cancionService;

    @Autowired
    private CancionUsuarioMapper cancionUsuarioMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldInsertCancionUsuarioWhenUpdateReproduccion() {
        CancionUsuarioServiceImpl cancionUsuarioService = new CancionUsuarioServiceImpl(new CancionUsuarioPersistenceImpl(cancionUsuarioRepository), cancionService, cancionUsuarioMapper);

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
        CancionUsuarioServiceImpl cancionUsuarioService = new CancionUsuarioServiceImpl(new CancionUsuarioPersistenceImpl(cancionUsuarioRepository), cancionService, cancionUsuarioMapper);

        CancionUsuarioDTO cancionUsuarioDTO = new CancionUsuarioDTO();
        cancionUsuarioDTO.setUsuarioId(99991L);
        cancionUsuarioDTO.setCancionId(99991L);

        CancionUsuarioDTO cancionUsuarioDTOModified = cancionUsuarioService.updateReproduccion(cancionUsuarioDTO);

        assertNotNull(cancionUsuarioDTOModified);
        assertEquals(99991, cancionUsuarioDTOModified.getUsuarioId());
        assertEquals(99991, cancionUsuarioDTOModified.getCancionId());
        assertEquals(5, cancionUsuarioDTOModified.getReproducciones());
    }

    @Test
    void shouldReturnDTOCancionUsuarioUpdatedWhenUpdateReproduccion() {
        CancionUsuarioDTO cancionUsuarioDTO = new CancionUsuarioDTO();
        cancionUsuarioDTO.setReproducciones(4L);

        CancionUsuarioDTO cancionUsuarioDTOUpdated = new CancionUsuarioDTO();
        cancionUsuarioDTOUpdated.setReproducciones(5L);

        CancionUsuario cancionUsuario = new CancionUsuario();
        cancionUsuario.setReproducciones(4L);

        CancionUsuario cancionUsuarioUpdated = new CancionUsuario();
        cancionUsuarioUpdated.setReproducciones(5L);

        CancionUsuarioMapper mockedCancionUsuarioMapper = mock(CancionUsuarioMapper.class);
        when(mockedCancionUsuarioMapper.toEntity(cancionUsuarioDTO)).thenReturn(cancionUsuario);
        when(mockedCancionUsuarioMapper.toDto(cancionUsuarioUpdated)).thenReturn(cancionUsuarioDTOUpdated);

        CancionUsuarioPersistence mockedCancionUsuarioPersistence = mock(CancionUsuarioPersistence.class);
        CancionService mockedCancionService = mock(CancionService.class);
        when(mockedCancionUsuarioPersistence.saveItem(cancionUsuario)).thenReturn(cancionUsuarioUpdated);

        CancionUsuarioService cancionUsuarioService = new CancionUsuarioServiceImpl(mockedCancionUsuarioPersistence, mockedCancionService, mockedCancionUsuarioMapper);

        CancionUsuarioDTO cancionUsuarioUpdatedDTO = cancionUsuarioService.updateReproduccion(cancionUsuarioDTO);

        assertNotNull(cancionUsuarioUpdatedDTO);
        assertEquals(5L, cancionUsuarioUpdatedDTO.getReproducciones());
    }
}
