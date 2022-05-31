package com.kreitek.PFBKreitekfy.Application.Service.CancionServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionSimpleDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionSimpleMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionSimpleMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.CancionServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.CancionRepository;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl.CancionPersistenceImpl;
import com.kreitek.PFBKreitekfy.PfbKreitekfyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class CancionServiceImplGetCancionesByCriteriaStringTest {
    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private CancionSimpleMapper cancionSimpleMapper;

    @Autowired
    private CancionMapper cancionMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldReturnCancionWhenFindAllCanciones() {
        CancionServiceImpl cancionService = new CancionServiceImpl(new CancionPersistenceImpl(cancionRepository), cancionSimpleMapper, cancionMapper);
        Pageable pageable = PageRequest.of(0, 15);
        Page<CancionSimpleDTO> cancionDTO = cancionService.getCancionesByCriteriaString(pageable, "");

        assertNotNull(cancionDTO);
        assertEquals(6, cancionDTO.getContent().size());
        assertEquals(99991, cancionDTO.getContent().get(0).getId());
        assertEquals("_-_cancion1_-_", cancionDTO.getContent().get(0).getNombre());
    }

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
