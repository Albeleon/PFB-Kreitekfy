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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class EstiloServiceImplGetEstilosByCriteriaStringIntegrationTest {
    @Autowired
    private EstiloRepository estiloRepository;

    @Autowired
    private EstiloMapper estiloMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldReturnEstiloWhenFindAllEstiloes() {
        EstiloServiceImpl estiloService = new EstiloServiceImpl(new EstiloPersistenceImpl(estiloRepository), estiloMapper);
        Pageable pageable = PageRequest.of(0, 5);
        Page<EstiloDTO> estiloDTO = estiloService.getEstilosByCriteriaString(pageable, "");

        assertNotNull(estiloDTO);
        assertEquals(3, estiloDTO.getContent().size());
        assertEquals(99991, estiloDTO.getContent().get(0).getId());
        assertEquals("_-_estilo_-_", estiloDTO.getContent().get(0).getNombre());
    }
}
