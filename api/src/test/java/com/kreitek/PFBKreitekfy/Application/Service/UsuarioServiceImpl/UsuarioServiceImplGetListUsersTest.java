package com.kreitek.PFBKreitekfy.Application.Service.UsuarioServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.UsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.UsuarioMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.UsuarioMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.UsuarioServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Usuario;
import com.kreitek.PFBKreitekfy.Domain.Persistence.UsuarioPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl.UsuarioPersistenceImpl;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.UsuarioRepository;
import com.kreitek.PFBKreitekfy.PfbKreitekfyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class UsuarioServiceImplGetListUsersTest {
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

    @Test
    void shouldReturnEmptyDTOListWhenRepositoryHasNoUsers() {
        UsuarioPersistence mockedUsuarioPersistence = mock(UsuarioPersistence.class);
        when(mockedUsuarioPersistence.getAllUsers()).thenReturn(new ArrayList<>());
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(new UsuarioMapperImpl(), mockedUsuarioPersistence);

        List<UsuarioDTO> usuarioDTOS = usuarioService.getListUsers();

        assertNotNull(usuarioDTOS);
        assertEquals(0, usuarioDTOS.size());
    }

    @Test
    void shouldReturnTwoDTOListWhenRepositoryHasTwoUsers() {

        List<Usuario> list = new ArrayList<>();
        list.add(new Usuario());
        list.add(new Usuario());

        UsuarioPersistence mockedUsuarioPersistence = mock(UsuarioPersistence.class);
        when(mockedUsuarioPersistence.getAllUsers()).thenReturn(list);
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(new UsuarioMapperImpl(), mockedUsuarioPersistence);

        List<UsuarioDTO> usuarioDTOS = usuarioService.getListUsers();

        assertNotNull(usuarioDTOS);
        assertEquals(2, usuarioDTOS.size());
    }
}
