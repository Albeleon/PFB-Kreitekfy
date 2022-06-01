package com.kreitek.PFBKreitekfy.IntegrationTests.Application.Service.UsuarioServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.UsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.UsuarioMapper;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.UsuarioServiceImpl;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl.UsuarioPersistenceImpl;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.UsuarioRepository;
import com.kreitek.PFBKreitekfy.PfbKreitekfyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class UsuarioServiceImplGetListUsersIntegrationTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Sql({"/test_data.sql"})
    @Sql(scripts = "/test_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void ShouldReturnUsuarioWhenFindAllUsuarios() {
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(usuarioMapper, new UsuarioPersistenceImpl(usuarioRepository));
        List<UsuarioDTO> usuarioDTO = usuarioService.getListUsers();

        assertNotNull(usuarioDTO);
        assertEquals(1, usuarioDTO.size());
        assertEquals(99991, usuarioDTO.get(0).getId());
        assertEquals("_-_usuario_-_", usuarioDTO.get(0).getNombre());
    }
}
