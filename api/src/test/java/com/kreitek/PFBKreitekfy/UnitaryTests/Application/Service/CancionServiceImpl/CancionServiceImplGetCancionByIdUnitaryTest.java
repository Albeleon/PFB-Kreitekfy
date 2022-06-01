package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.CancionServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionSimpleMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.CancionServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionPersistence;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CancionServiceImplGetCancionByIdUnitaryTest {

    @Test
    void shouldReturnEmptyDTOcancionWhenRepositoryHasNotThatCancion() {
        CancionPersistence mockedCancionPersistence = mock(CancionPersistence.class);
        when(mockedCancionPersistence.getCancionById(1L)).thenReturn(Optional.empty());
        CancionServiceImpl cancionService = new CancionServiceImpl(mockedCancionPersistence, new CancionSimpleMapperImpl(), new CancionMapperImpl());

        Optional<CancionDTO> cancionDTO = cancionService.getCancionById(1L);

        assertNotNull(cancionDTO);
        assertFalse(cancionDTO.isPresent());
    }

    @Test
    void shouldReturnDTOcancionWhenRepositoryHasThatCancion() {
        CancionPersistence mockedCancionPersistence = mock(CancionPersistence.class);
        when(mockedCancionPersistence.getCancionById(1L)).thenReturn(Optional.of(new Cancion()));
        CancionServiceImpl cancionService = new CancionServiceImpl(mockedCancionPersistence, new CancionSimpleMapperImpl(), new CancionMapperImpl());

        Optional<CancionDTO> cancionDTO = cancionService.getCancionById(1L);

        assertNotNull(cancionDTO);
        assertTrue(cancionDTO.isPresent());
    }
}
