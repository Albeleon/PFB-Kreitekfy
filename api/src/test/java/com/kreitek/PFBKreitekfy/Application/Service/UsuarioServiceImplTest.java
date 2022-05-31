package com.kreitek.PFBKreitekfy.Application.Service;

import com.kreitek.PFBKreitekfy.Application.Dto.UsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.UsuarioMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.UsuarioServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Usuario;
import com.kreitek.PFBKreitekfy.Domain.Persistence.UsuarioPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UsuarioServiceImplTest {
    UsuarioPersistence mockedUsuarioPersistence;

    @BeforeEach
    void initialize() {
        mockedUsuarioPersistence = mock(UsuarioPersistence.class);
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
        UsuarioPersistence mockedUsuarioPersistence = mock(UsuarioPersistence.class);

        List<Usuario> list = new ArrayList<>();
        list.add(new Usuario());
        list.add(new Usuario());

        when(mockedUsuarioPersistence.getAllUsers()).thenReturn(list);
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(new UsuarioMapperImpl(), mockedUsuarioPersistence);

        List<UsuarioDTO> usuarioDTOS = usuarioService.getListUsers();

        assertNotNull(usuarioDTOS);
        assertEquals(2, usuarioDTOS.size());
    }

    @Test
    void shouldReturnEmptyDTOUserWhenRepositoryHasNotThatUser() {
        UsuarioPersistence mockedUsuarioPersistence = mock(UsuarioPersistence.class);

        when(mockedUsuarioPersistence.getUsuarioById(1L)).thenReturn(Optional.empty());
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(new UsuarioMapperImpl(), mockedUsuarioPersistence);

        Optional<UsuarioDTO> usuarioDTO = usuarioService.getUsuarioById(1L);

        assertNotNull(usuarioDTO);
        assertFalse(usuarioDTO.isPresent());
    }

    @Test
    void shouldReturnDTOUserWhenRepositoryHasThatUser() {
        UsuarioPersistence mockedUsuarioPersistence = mock(UsuarioPersistence.class);

        when(mockedUsuarioPersistence.getUsuarioById(1L)).thenReturn(Optional.of(new Usuario()));
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(new UsuarioMapperImpl(), mockedUsuarioPersistence);

        Optional<UsuarioDTO> usuarioDTO = usuarioService.getUsuarioById(1L);

        assertNotNull(usuarioDTO);
        assertTrue(usuarioDTO.isPresent());
    }
}