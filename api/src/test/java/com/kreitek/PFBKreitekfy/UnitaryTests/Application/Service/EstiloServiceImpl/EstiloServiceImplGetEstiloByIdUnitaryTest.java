package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.EstiloServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.EstiloDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.EstiloMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.EstiloServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Estilo;
import com.kreitek.PFBKreitekfy.Domain.Persistence.EstiloPersistence;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EstiloServiceImplGetEstiloByIdUnitaryTest {

    @Test
    void shouldReturnEmptyDTOestiloWhenRepositoryHasNotThatestilo() {
        EstiloPersistence mockedEstiloPersistence = mock(EstiloPersistence.class);
        when(mockedEstiloPersistence.getEstiloById(1L)).thenReturn(Optional.empty());
        EstiloServiceImpl estiloService = new EstiloServiceImpl(mockedEstiloPersistence, new EstiloMapperImpl());

        Optional<EstiloDTO> estiloDTO = estiloService.getEstiloById(1L);

        assertNotNull(estiloDTO);
        assertFalse(estiloDTO.isPresent());
    }

    @Test
    void shouldReturnDTOestiloWhenRepositoryHasThatestilo() {
        EstiloPersistence mockedEstiloPersistence = mock(EstiloPersistence.class);
        when(mockedEstiloPersistence.getEstiloById(1L)).thenReturn(Optional.of(new Estilo()));
        EstiloServiceImpl estiloService = new EstiloServiceImpl(mockedEstiloPersistence, new EstiloMapperImpl());

        Optional<EstiloDTO> estiloDTO = estiloService.getEstiloById(1L);

        assertNotNull(estiloDTO);
        assertTrue(estiloDTO.isPresent());
    }
}
