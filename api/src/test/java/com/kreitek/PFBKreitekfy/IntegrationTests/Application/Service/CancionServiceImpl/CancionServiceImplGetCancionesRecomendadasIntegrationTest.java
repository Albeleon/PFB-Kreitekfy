package com.kreitek.PFBKreitekfy.IntegrationTests.Application.Service.CancionServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionSimpleDTO;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class CancionServiceImplGetCancionesRecomendadasIntegrationTest {
    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private CancionSimpleMapper cancionSimpleMapper;

    @Autowired
    private CancionMapper cancionMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldReturn1ParticularCancionWhenFindRecomendadas() {
        CancionServiceImpl cancionService = new CancionServiceImpl(new CancionPersistenceImpl(cancionRepository), cancionSimpleMapper, cancionMapper);
        List<CancionSimpleDTO> cancionesDTO = cancionService.getCancionesRecomendadas(99991L);

        assertNotNull(cancionesDTO);
        assertEquals(1, cancionesDTO.size());
        assertEquals(99991, cancionesDTO.get(0).getId());
    }
}
