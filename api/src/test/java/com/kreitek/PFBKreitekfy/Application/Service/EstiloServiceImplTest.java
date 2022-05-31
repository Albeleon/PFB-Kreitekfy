package com.kreitek.PFBKreitekfy.Application.Service;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionUsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.EstiloDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionUsuarioMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.EstiloMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.EstiloMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.CancionUsuarioServiceImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.EstiloServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import com.kreitek.PFBKreitekfy.Domain.Entity.Estilo;
import com.kreitek.PFBKreitekfy.Domain.Key.CancionUsuarioKey;
import com.kreitek.PFBKreitekfy.Domain.Persistence.EstiloPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EstiloServiceImplTest {
    EstiloPersistence mockedEstiloPersistence;

    @BeforeEach
    void initialize() {
        mockedEstiloPersistence = mock(EstiloPersistence.class);
    }

    @Test
    void shouldReturnEmptyDTOListWhenRepositoryHasNoestilos() {
        Pageable pageable = PageRequest.of(0, 5);

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

        when(mockedEstiloPersistence.findAll(pageable, "")).thenReturn(new PageImpl<>(estilos, pageable, 2));
        EstiloServiceImpl estiloService = new EstiloServiceImpl(mockedEstiloPersistence, new EstiloMapperImpl());

        Page<EstiloDTO> estiloDTOS = estiloService.getEstilosByCriteriaString(pageable, "");

        assertNotNull(estiloDTOS);
        assertNotNull(estiloDTOS.getContent());
        assertEquals(2, estiloDTOS.getTotalElements());
        assertEquals(2, estiloDTOS.getContent().size());
    }

    @Test
    void shouldReturnEmptyDTOestiloWhenRepositoryHasNotThatestilo() {
        when(mockedEstiloPersistence.getEstiloById(1L)).thenReturn(Optional.empty());
        EstiloServiceImpl estiloService = new EstiloServiceImpl(mockedEstiloPersistence, new EstiloMapperImpl());

        Optional<EstiloDTO> estiloDTO = estiloService.getEstiloById(1L);

        assertNotNull(estiloDTO);
        assertFalse(estiloDTO.isPresent());
    }

    @Test
    void shouldReturnDTOestiloWhenRepositoryHasThatestilo() {
        when(mockedEstiloPersistence.getEstiloById(1L)).thenReturn(Optional.of(new Estilo()));
        EstiloServiceImpl estiloService = new EstiloServiceImpl(mockedEstiloPersistence, new EstiloMapperImpl());

        Optional<EstiloDTO> estiloDTO = estiloService.getEstiloById(1L);

        assertNotNull(estiloDTO);
        assertTrue(estiloDTO.isPresent());
    }

    @Test
    void shouldReturnDTOEstiloWhenRepositorySavesEstilo() {

        EstiloDTO estiloDTO = new EstiloDTO();
        EstiloDTO estiloDTOUpdated = new EstiloDTO();

        Estilo estilo = new Estilo();
        Estilo estiloUpdated = new Estilo();

        EstiloMapper mockedEstiloMapper = mock(EstiloMapper.class);
        when(mockedEstiloMapper.toEntity(estiloDTO)).thenReturn(estilo);
        when(mockedEstiloMapper.toDto(estiloUpdated)).thenReturn(estiloDTOUpdated);

        when(mockedEstiloPersistence.saveEstilo(estilo)).thenReturn(estiloUpdated);

        EstiloServiceImpl estiloService = new EstiloServiceImpl(mockedEstiloPersistence, mockedEstiloMapper);

        EstiloDTO estiloUpdatedDTO = estiloService.saveEstilo(estiloDTO);

        assertNotNull(estiloUpdatedDTO);
    }
}