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

public class CancionUsuarioServiceImplUpdateValoracionUnitaryTest {

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

        CancionUsuarioPersistence mockedCancionUsuarioPersistence = mock(CancionUsuarioPersistence.class);
        CancionService mockedCancionService = mock(CancionService.class);
        when(mockedCancionUsuarioPersistence.saveItem(cancionUsuario)).thenReturn(cancionUsuarioUpdated);

        CancionUsuarioService cancionUsuarioService = new CancionUsuarioServiceImpl(mockedCancionUsuarioPersistence, mockedCancionService, mockedCancionUsuarioMapper, new CancionMapperImpl());

        CancionUsuarioDTO cancionUsuarioUpdatedDTO = cancionUsuarioService.updateReproduccion(cancionUsuarioDTO);

        assertNotNull(cancionUsuarioUpdatedDTO);
        assertEquals(3L, cancionUsuarioUpdatedDTO.getValoracion());
    }
}
