package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.EstiloServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.EstiloDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.EstiloMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.EstiloServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Estilo;
import com.kreitek.PFBKreitekfy.Domain.Persistence.EstiloPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EstiloServiceImplGetEstilosByCriteriaStringUnitaryTest {

    @Test
    void shouldReturnEmptyDTOListWhenRepositoryHasNoestilos() {
        Pageable pageable = PageRequest.of(0, 5);

        EstiloPersistence mockedEstiloPersistence = mock(EstiloPersistence.class);
        when(mockedEstiloPersistence.findAll(pageable, "")).thenReturn(new PageImpl<>(new ArrayList<>(), pageable, 0));
        EstiloServiceImpl estiloService = new EstiloServiceImpl(mockedEstiloPersistence, new EstiloMapperImpl());

        Page<EstiloDTO> estiloDTOS = estiloService.getEstilosByCriteriaString(pageable, "");

        assertNotNull(estiloDTOS);
        assertNotNull(estiloDTOS.getContent());
        assertEquals(0, estiloDTOS.getTotalElements());
        assertEquals(0, estiloDTOS.getContent().size());
    }

    @Test
    void shouldReturnTwoDTOListWhenRepositoryHasTwoestilos() {
        Pageable pageable = PageRequest.of(0, 5);

        List<Estilo> estilos = new ArrayList<>();
        estilos.add(new Estilo());
        estilos.add(new Estilo());

        EstiloPersistence mockedEstiloPersistence = mock(EstiloPersistence.class);
        when(mockedEstiloPersistence.findAll(pageable, "")).thenReturn(new PageImpl<>(estilos, pageable, 2));
        EstiloServiceImpl estiloService = new EstiloServiceImpl(mockedEstiloPersistence, new EstiloMapperImpl());

        Page<EstiloDTO> estiloDTOS = estiloService.getEstilosByCriteriaString(pageable, "");

        assertNotNull(estiloDTOS);
        assertNotNull(estiloDTOS.getContent());
        assertEquals(2, estiloDTOS.getTotalElements());
        assertEquals(2, estiloDTOS.getContent().size());
    }
}
