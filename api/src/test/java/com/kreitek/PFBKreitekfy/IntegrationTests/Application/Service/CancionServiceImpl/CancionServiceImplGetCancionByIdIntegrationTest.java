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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class CancionServiceImplGetCancionByIdIntegrationTest {
    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private CancionSimpleMapper cancionSimpleMapper;

    @Autowired
    private CancionMapper cancionMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldReturnCancionWhenFindOneCancion() {
        CancionServiceImpl cancionService = new CancionServiceImpl(new CancionPersistenceImpl(cancionRepository), cancionSimpleMapper, cancionMapper);
        Optional<CancionDTO> cancionDTO = cancionService.getCancionById(99991L);

        assertNotNull(cancionDTO);
        assertTrue(cancionDTO.isPresent());
        assertEquals(99991, cancionDTO.get().getId());
        assertEquals("_-_cancion1_-_", cancionDTO.get().getNombre());
    }

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldReturnNoCancionWhenFindOneCancionThatIsNot() {
        CancionServiceImpl cancionService = new CancionServiceImpl(new CancionPersistenceImpl(cancionRepository), cancionSimpleMapper, cancionMapper);
        Optional<CancionDTO> cancionDTO = cancionService.getCancionById(99990L);

        assertNotNull(cancionDTO);
        assertFalse(cancionDTO.isPresent());
    }
}
