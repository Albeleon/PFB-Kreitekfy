package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.EstiloServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.EstiloDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.EstiloMapper;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.EstiloServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Estilo;
import com.kreitek.PFBKreitekfy.Domain.Persistence.EstiloPersistence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EstiloServiceImplSaveEstiloUnitaryTest {

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
