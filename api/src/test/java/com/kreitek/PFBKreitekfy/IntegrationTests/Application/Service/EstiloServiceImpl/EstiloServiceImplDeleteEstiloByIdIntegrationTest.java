package com.kreitek.PFBKreitekfy.IntegrationTests.Application.Service.EstiloServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.EstiloDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.EstiloMapper;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.EstiloServiceImpl;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.EstiloRepository;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl.EstiloPersistenceImpl;
import com.kreitek.PFBKreitekfy.PfbKreitekfyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class EstiloServiceImplDeleteEstiloByIdIntegrationTest {
    @Autowired
    private EstiloRepository estiloRepository;

    @Autowired
    private EstiloMapper estiloMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldDeleteEstiloWhenDeleteItem() {
        EstiloServiceImpl estiloService = new EstiloServiceImpl(new EstiloPersistenceImpl(estiloRepository), estiloMapper);

        estiloService.deleteEstiloById(99991L);
        Optional<EstiloDTO> estiloDTODeleted = estiloService.getEstiloById(99991L);

        assertNotNull(estiloDTODeleted);
        assertFalse(estiloDTODeleted.isPresent());
    }
}
