package com.kreitek.PFBKreitekfy.Application.Service;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.CancionDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.CancionSimpleDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionSimpleMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionSimpleMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.CancionServiceImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.CancionServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CancionServiceImplTest {
    CancionPersistence mockedCancionPersistence;

    @BeforeEach
    void initialize() {
        mockedCancionPersistence = mock(CancionPersistence.class);
    }

    @Test
    void shouldReturnEmptyDTOListWhenRepositoryHasNoCancions() {
        Pageable pageable = PageRequest.of(0, 5);

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

        when(mockedCancionPersistence.findAll(pageable, "")).thenReturn(new PageImpl<>(cancions, pageable, 2));
        CancionServiceImpl cancionService = new CancionServiceImpl(mockedCancionPersistence, new CancionSimpleMapperImpl(), new CancionMapperImpl());

        Page<CancionSimpleDTO> cancionDTOS = cancionService.getCancionesByCriteriaString(pageable, "");

        assertNotNull(cancionDTOS);
        assertNotNull(cancionDTOS.getContent());
        assertEquals(2, cancionDTOS.getTotalElements());
        assertEquals(2, cancionDTOS.getContent().size());
    }

    @Test
    void shouldReturnEmptyDTOcancionWhenRepositoryHasNotThatCancion() {
        when(mockedCancionPersistence.getCancionById(1L)).thenReturn(Optional.empty());
        CancionServiceImpl cancionService = new CancionServiceImpl(mockedCancionPersistence, new CancionSimpleMapperImpl(), new CancionMapperImpl());

        Optional<CancionDTO> cancionDTO = cancionService.getCancionById(1L);

        assertNotNull(cancionDTO);
        assertFalse(cancionDTO.isPresent());
    }

    @Test
    void shouldReturnDTOcancionWhenRepositoryHasThatCancion() {
        when(mockedCancionPersistence.getCancionById(1L)).thenReturn(Optional.of(new Cancion()));
        CancionServiceImpl cancionService = new CancionServiceImpl(mockedCancionPersistence, new CancionSimpleMapperImpl(), new CancionMapperImpl());

        Optional<CancionDTO> cancionDTO = cancionService.getCancionById(1L);

        assertNotNull(cancionDTO);
        assertTrue(cancionDTO.isPresent());
    }

    @Test
    void shouldReturnDTOCancionWhenRepositorySavesCancion() {

        CancionDTO cancionDTO = new CancionDTO();
        CancionDTO cancionDTOUpdated = new CancionDTO();

        Cancion cancion = new Cancion();
        Cancion cancionUpdated = new Cancion();

        CancionMapper mockedCancionMapper = mock(CancionMapper.class);
        when(mockedCancionMapper.toEntity(cancionDTO)).thenReturn(cancion);
        when(mockedCancionMapper.toDto(cancionUpdated)).thenReturn(cancionDTOUpdated);

        when(mockedCancionPersistence.saveItem(cancion)).thenReturn(cancionUpdated);

        CancionServiceImpl cancionService = new CancionServiceImpl(mockedCancionPersistence, new CancionSimpleMapperImpl(), mockedCancionMapper);

        CancionDTO cancionUpdatedDTO = cancionService.saveItem(cancionDTO);

        assertNotNull(cancionUpdatedDTO);
    }
}