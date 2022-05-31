package com.kreitek.PFBKreitekfy.Application.Service.EstiloServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.EstiloDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.EstiloMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.EstiloMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.EstiloServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Estilo;
import com.kreitek.PFBKreitekfy.Domain.Persistence.EstiloPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.EstiloRepository;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl.EstiloPersistenceImpl;
import com.kreitek.PFBKreitekfy.PfbKreitekfyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class EstiloServiceImplGetEstiloByIdTest {
    @Autowired
    private EstiloRepository estiloRepository;

    @Autowired
    private EstiloMapper estiloMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldReturnNoEstiloWhenFindOneEstiloThatIsNot() {
        EstiloServiceImpl estiloService = new EstiloServiceImpl(new EstiloPersistenceImpl(estiloRepository), estiloMapper);
        Optional<EstiloDTO> estiloDTO = estiloService.getEstiloById(99990L);

        assertNotNull(estiloDTO);
        assertFalse(estiloDTO.isPresent());
    }

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldReturnEstiloWhenFindOneEstilo() {
        EstiloServiceImpl estiloService = new EstiloServiceImpl(new EstiloPersistenceImpl(estiloRepository), estiloMapper);
        Optional<EstiloDTO> estiloDTO = estiloService.getEstiloById(99991L);

        assertNotNull(estiloDTO);
        assertTrue(estiloDTO.isPresent());
        assertEquals(99991, estiloDTO.get().getId());
        assertEquals("_-_estilo_-_", estiloDTO.get().getNombre());
    }

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
