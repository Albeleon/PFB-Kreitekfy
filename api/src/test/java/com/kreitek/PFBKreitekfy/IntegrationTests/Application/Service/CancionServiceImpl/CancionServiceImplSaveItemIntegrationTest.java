package com.kreitek.PFBKreitekfy.IntegrationTests.Application.Service.CancionServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionSimpleMapper;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.CancionServiceImpl;
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

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class CancionServiceImplSaveItemIntegrationTest {
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
}
