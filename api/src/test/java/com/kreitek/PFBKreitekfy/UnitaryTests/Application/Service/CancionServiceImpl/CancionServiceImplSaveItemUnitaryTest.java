package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.CancionServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionSimpleMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.CancionServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionPersistence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CancionServiceImplSaveItemUnitaryTest {

    @Test
    void shouldReturnDTOCancionWhenRepositorySavesCancion() {

        CancionDTO cancionDTO = new CancionDTO();
        CancionDTO cancionDTOUpdated = new CancionDTO();

        Cancion cancion = new Cancion();
        Cancion cancionUpdated = new Cancion();

        CancionMapper mockedCancionMapper = mock(CancionMapper.class);
        when(mockedCancionMapper.toEntity(cancionDTO)).thenReturn(cancion);
        when(mockedCancionMapper.toDto(cancionUpdated)).thenReturn(cancionDTOUpdated);

        CancionPersistence mockedCancionPersistence = mock(CancionPersistence.class);
        when(mockedCancionPersistence.saveItem(cancion)).thenReturn(cancionUpdated);

        CancionServiceImpl cancionService = new CancionServiceImpl(mockedCancionPersistence, new CancionSimpleMapperImpl(), mockedCancionMapper);

        CancionDTO cancionUpdatedDTO = cancionService.saveItem(cancionDTO);

        assertNotNull(cancionUpdatedDTO);
    }
}
