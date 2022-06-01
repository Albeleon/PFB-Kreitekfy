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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class CancionServiceImplDeleteCancionByIdIntegrationTest {
    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private CancionSimpleMapper cancionSimpleMapper;

    @Autowired
    private CancionMapper cancionMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldDeleteCancionWhenDeleteItem() {
        CancionServiceImpl cancionService = new CancionServiceImpl(new CancionPersistenceImpl(cancionRepository), cancionSimpleMapper, cancionMapper);

        cancionService.deleteCancionById(99991L);
        Optional<CancionDTO> cancionDTODeleted = cancionService.getCancionById(99991L);

        assertNotNull(cancionDTODeleted);
        assertFalse(cancionDTODeleted.isPresent());
    }
}
