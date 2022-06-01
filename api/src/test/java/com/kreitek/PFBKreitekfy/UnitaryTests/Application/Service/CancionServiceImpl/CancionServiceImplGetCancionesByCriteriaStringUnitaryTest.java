package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.CancionServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionSimpleDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionSimpleMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.CancionServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionPersistence;
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

public class CancionServiceImplGetCancionesByCriteriaStringUnitaryTest {

    @Test
    void shouldReturnEmptyDTOListWhenRepositoryHasNoCancions() {
        Pageable pageable = PageRequest.of(0, 5);

        CancionPersistence mockedCancionPersistence = mock(CancionPersistence.class);
        when(mockedCancionPersistence.findAll(pageable, "")).thenReturn(new PageImpl<>(new ArrayList<>(), pageable, 0));
        CancionServiceImpl cancionService = new CancionServiceImpl(mockedCancionPersistence, new CancionSimpleMapperImpl(), new CancionMapperImpl());

        Page<CancionSimpleDTO> cancionDTOS = cancionService.getCancionesByCriteriaString(pageable, "");

        assertNotNull(cancionDTOS);
        assertNotNull(cancionDTOS.getContent());
        assertEquals(0, cancionDTOS.getTotalElements());
        assertEquals(0, cancionDTOS.getContent().size());
    }

    @Test
    void shouldReturnTwoDTOListWhenRepositoryHasTwoCanciones() {
        Pageable pageable = PageRequest.of(0, 5);

        List<Cancion> cancions = new ArrayList<>();
        cancions.add(new Cancion());
        cancions.add(new Cancion());

        CancionPersistence mockedCancionPersistence = mock(CancionPersistence.class);
        when(mockedCancionPersistence.findAll(pageable, "")).thenReturn(new PageImpl<>(cancions, pageable, 2));
        CancionServiceImpl cancionService = new CancionServiceImpl(mockedCancionPersistence, new CancionSimpleMapperImpl(), new CancionMapperImpl());

        Page<CancionSimpleDTO> cancionDTOS = cancionService.getCancionesByCriteriaString(pageable, "");

        assertNotNull(cancionDTOS);
        assertNotNull(cancionDTOS.getContent());
        assertEquals(2, cancionDTOS.getTotalElements());
        assertEquals(2, cancionDTOS.getContent().size());
    }
}
