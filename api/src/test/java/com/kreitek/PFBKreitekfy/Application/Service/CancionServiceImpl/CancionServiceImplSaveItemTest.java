package com.kreitek.PFBKreitekfy.Application.Service.CancionServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionMapper;
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
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class CancionServiceImplSaveItemTest {
    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private CancionSimpleMapper cancionSimpleMapper;

    @Autowired
    private CancionMapper cancionMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldInsertCancionWhenSaveItem() {
        CancionServiceImpl cancionService = new CancionServiceImpl(new CancionPersistenceImpl(cancionRepository), cancionSimpleMapper, cancionMapper);

        CancionDTO cancionDTO = new CancionDTO();
        cancionDTO.setNombre("Nuevo cancion");
        cancionDTO.setDuracion(40L);
        cancionDTO.setFecha(new Date());
        cancionDTO.setReproduccion(0L);
        cancionDTO.setAlbumId(99991L);
        cancionDTO.setEstiloId(99991L);
        cancionDTO.setArtistaId(99991L);
        CancionDTO cancionDTOInserted = cancionService.saveItem(cancionDTO);

        assertNotNull(cancionDTOInserted);
        assertEquals("Nuevo cancion", cancionDTOInserted.getNombre());
    }

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldModifyCancionWhenSaveItem() {
        CancionServiceImpl cancionService = new CancionServiceImpl(new CancionPersistenceImpl(cancionRepository), cancionSimpleMapper, cancionMapper);

        CancionDTO cancionDTO = new CancionDTO();
        cancionDTO.setId(99991L);
        cancionDTO.setNombre("Cancion modificado");
        cancionDTO.setDuracion(40L);
        cancionDTO.setFecha(new Date());
        cancionDTO.setReproduccion(0L);
        cancionDTO.setAlbumId(99991L);
        cancionDTO.setEstiloId(99991L);
        cancionDTO.setArtistaId(99991L);
        CancionDTO cancionDTOModified = cancionService.saveItem(cancionDTO);

        assertNotNull(cancionDTOModified);
        assertEquals("Cancion modificado", cancionDTOModified.getNombre());
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

        CancionPersistence mockedCancionPersistence = mock(CancionPersistence.class);
        when(mockedCancionPersistence.saveItem(cancion)).thenReturn(cancionUpdated);

        CancionServiceImpl cancionService = new CancionServiceImpl(mockedCancionPersistence, new CancionSimpleMapperImpl(), mockedCancionMapper);

        CancionDTO cancionUpdatedDTO = cancionService.saveItem(cancionDTO);

        assertNotNull(cancionUpdatedDTO);
    }
}
