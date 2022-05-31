package com.kreitek.PFBKreitekfy.Application.Service.EstiloServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.EstiloDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.EstiloMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class EstiloServiceImplSaveEstiloTest {
    @Autowired
    private EstiloRepository estiloRepository;

    @Autowired
    private EstiloMapper estiloMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldInsertEstiloWhenSaveItem() {
        EstiloServiceImpl estiloService = new EstiloServiceImpl(new EstiloPersistenceImpl(estiloRepository), estiloMapper);

        EstiloDTO estiloDTO = new EstiloDTO();
        estiloDTO.setNombre("Nuevo estilo");
        EstiloDTO estiloDTOInserted = estiloService.saveEstilo(estiloDTO);

        assertNotNull(estiloDTOInserted);
        assertEquals("Nuevo estilo", estiloDTOInserted.getNombre());
    }

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldModifyEstiloWhenSaveItem() {
        EstiloServiceImpl estiloService = new EstiloServiceImpl(new EstiloPersistenceImpl(estiloRepository), estiloMapper);

        EstiloDTO estiloDTO = new EstiloDTO();
        estiloDTO.setId(99991L);
        estiloDTO.setNombre("Estilo modificado");
        EstiloDTO estiloDTOModified = estiloService.saveEstilo(estiloDTO);

        assertNotNull(estiloDTOModified);
        assertEquals("Estilo modificado", estiloDTOModified.getNombre());
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

        EstiloPersistence mockedEstiloPersistence = mock(EstiloPersistence.class);
        when(mockedEstiloPersistence.saveEstilo(estilo)).thenReturn(estiloUpdated);

        EstiloServiceImpl estiloService = new EstiloServiceImpl(mockedEstiloPersistence, mockedEstiloMapper);

        EstiloDTO estiloUpdatedDTO = estiloService.saveEstilo(estiloDTO);

        assertNotNull(estiloUpdatedDTO);
    }
}
