package com.kreitek.PFBKreitekfy.Application.Service;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionUsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.EstiloDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionUsuarioMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionUsuarioMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Mapper.EstiloMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.CancionUsuarioServiceImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.EstiloServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import com.kreitek.PFBKreitekfy.Domain.Entity.Estilo;
import com.kreitek.PFBKreitekfy.Domain.Key.CancionUsuarioKey;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionUsuarioPersistence;
import com.kreitek.PFBKreitekfy.Domain.Persistence.EstiloPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CancionUsuarioServiceImplTest {
    CancionUsuarioPersistence mockedCancionUsuarioPersistence;
    CancionService mockedCancionService;

    @BeforeEach
    void initialize() {
        mockedCancionUsuarioPersistence = mock(CancionUsuarioPersistence.class);
        mockedCancionService = mock(CancionService.class);
    }

    @Test
    void shouldReturnEmptyDTOCancionUsuarioWhenRepositoryHasNotThatElement() {
        when(mockedCancionUsuarioPersistence.findCancionUsuarioByCancionIdAndUsuarioId(1L, 1L)).thenReturn(Optional.empty());

        CancionUsuarioService cancionUsuarioService = new CancionUsuarioServiceImpl(mockedCancionUsuarioPersistence, mockedCancionService, new CancionUsuarioMapperImpl());

        Optional<CancionUsuarioDTO> cancionUsuarioDTO = cancionUsuarioService.getCancionUsuarioById(1L, 1L);

        assertNotNull(cancionUsuarioDTO);
        assertFalse(cancionUsuarioDTO.isPresent());
    }

    @Test
    void shouldReturnDTOCancionUsuarioWhenRepositoryHasThatElement() {
        when(mockedCancionUsuarioPersistence.findCancionUsuarioByCancionIdAndUsuarioId(1L, 1L)).thenReturn(Optional.of(new CancionUsuario()));

        CancionUsuarioService cancionUsuarioService = new CancionUsuarioServiceImpl(mockedCancionUsuarioPersistence, mockedCancionService, new CancionUsuarioMapperImpl());

        Optional<CancionUsuarioDTO> cancionUsuarioDTO = cancionUsuarioService.getCancionUsuarioById(1L, 1L);

        assertNotNull(cancionUsuarioDTO);
        assertTrue(cancionUsuarioDTO.isPresent());
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

        when(mockedCancionUsuarioPersistence.saveItem(cancionUsuario)).thenReturn(cancionUsuarioUpdated);

        CancionUsuarioService cancionUsuarioService = new CancionUsuarioServiceImpl(mockedCancionUsuarioPersistence, mockedCancionService, mockedCancionUsuarioMapper);

        CancionUsuarioDTO cancionUsuarioUpdatedDTO = cancionUsuarioService.updateReproduccion(cancionUsuarioDTO);

        assertNotNull(cancionUsuarioUpdatedDTO);
        assertEquals(5L, cancionUsuarioUpdatedDTO.getReproducciones());
    }

    @Test
    void shouldReturnDTOCancionUsuarioUpdatedWhenUpdateVarloacion() {
        CancionUsuarioDTO cancionUsuarioDTO = new CancionUsuarioDTO();
        cancionUsuarioDTO.setValoracion(3L);

        CancionUsuarioDTO cancionUsuarioDTOUpdated = new CancionUsuarioDTO();
        cancionUsuarioDTOUpdated.setValoracion(3L);

        CancionUsuario cancionUsuario = new CancionUsuario();
        cancionUsuario.setValoracion(3L);

        CancionUsuario cancionUsuarioUpdated = new CancionUsuario();
        cancionUsuarioUpdated.setValoracion(3L);

        CancionUsuarioMapper mockedCancionUsuarioMapper = mock(CancionUsuarioMapper.class);
        when(mockedCancionUsuarioMapper.toEntity(cancionUsuarioDTO)).thenReturn(cancionUsuario);
        when(mockedCancionUsuarioMapper.toDto(cancionUsuarioUpdated)).thenReturn(cancionUsuarioDTOUpdated);

        when(mockedCancionUsuarioPersistence.saveItem(cancionUsuario)).thenReturn(cancionUsuarioUpdated);

        CancionUsuarioService cancionUsuarioService = new CancionUsuarioServiceImpl(mockedCancionUsuarioPersistence, mockedCancionService, mockedCancionUsuarioMapper);

        CancionUsuarioDTO cancionUsuarioUpdatedDTO = cancionUsuarioService.updateReproduccion(cancionUsuarioDTO);

        assertNotNull(cancionUsuarioUpdatedDTO);
        assertEquals(3L, cancionUsuarioUpdatedDTO.getValoracion());
    }
}