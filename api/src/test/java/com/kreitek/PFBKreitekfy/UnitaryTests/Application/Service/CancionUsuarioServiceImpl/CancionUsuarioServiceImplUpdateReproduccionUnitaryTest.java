package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.CancionUsuarioServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionUsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionUsuarioMapper;
import com.kreitek.PFBKreitekfy.Application.Service.CancionService;
import com.kreitek.PFBKreitekfy.Application.Service.CancionUsuarioService;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.CancionUsuarioServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionUsuarioPersistence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CancionUsuarioServiceImplUpdateReproduccionUnitaryTest {

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

        CancionUsuarioService cancionUsuarioService = new CancionUsuarioServiceImpl(mockedCancionUsuarioPersistence, mockedCancionService, mockedCancionUsuarioMapper, new CancionMapperImpl());

        CancionUsuarioDTO cancionUsuarioUpdatedDTO = cancionUsuarioService.updateReproduccion(cancionUsuarioDTO);

        assertNotNull(cancionUsuarioUpdatedDTO);
        assertEquals(5L, cancionUsuarioUpdatedDTO.getReproducciones());
    }
}
