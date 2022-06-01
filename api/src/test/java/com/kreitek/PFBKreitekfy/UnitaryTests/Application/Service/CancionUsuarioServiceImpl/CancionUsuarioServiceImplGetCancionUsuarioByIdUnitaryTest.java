package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.CancionUsuarioServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionUsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionUsuarioMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.CancionService;
import com.kreitek.PFBKreitekfy.Application.Service.CancionUsuarioService;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.CancionUsuarioServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionPersistence;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionUsuarioPersistence;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CancionUsuarioServiceImplGetCancionUsuarioByIdUnitaryTest {

    @Test
    void shouldReturnEmptyDTOCancionUsuarioWhenRepositoryHasNotThatElement() {
        CancionPersistence cancionPersistence = mock(CancionPersistence.class);
        CancionUsuarioPersistence mockedCancionUsuarioPersistence = mock(CancionUsuarioPersistence.class);
        CancionService mockedCancionService = mock(CancionService.class);
        when(mockedCancionUsuarioPersistence.findCancionUsuarioByCancionIdAndUsuarioId(1L, 1L)).thenReturn(Optional.empty());

        CancionUsuarioService cancionUsuarioService = new CancionUsuarioServiceImpl(mockedCancionUsuarioPersistence, mockedCancionService, new CancionUsuarioMapperImpl(), new CancionMapperImpl(),cancionPersistence);

        Optional<CancionUsuarioDTO> cancionUsuarioDTO = cancionUsuarioService.getCancionUsuarioById(1L, 1L);

        assertNotNull(cancionUsuarioDTO);
        assertFalse(cancionUsuarioDTO.isPresent());
    }

    @Test
    void shouldReturnDTOCancionUsuarioWhenRepositoryHasThatElement() {
        CancionPersistence cancionPersistence = mock(CancionPersistence.class);
        CancionUsuarioPersistence mockedCancionUsuarioPersistence = mock(CancionUsuarioPersistence.class);
        CancionService mockedCancionService = mock(CancionService.class);
        when(mockedCancionUsuarioPersistence.findCancionUsuarioByCancionIdAndUsuarioId(1L, 1L)).thenReturn(Optional.of(new CancionUsuario()));

        CancionUsuarioService cancionUsuarioService = new CancionUsuarioServiceImpl(mockedCancionUsuarioPersistence, mockedCancionService, new CancionUsuarioMapperImpl(), new CancionMapperImpl(),cancionPersistence);

        Optional<CancionUsuarioDTO> cancionUsuarioDTO = cancionUsuarioService.getCancionUsuarioById(1L, 1L);

        assertNotNull(cancionUsuarioDTO);
        assertTrue(cancionUsuarioDTO.isPresent());
    }
}
