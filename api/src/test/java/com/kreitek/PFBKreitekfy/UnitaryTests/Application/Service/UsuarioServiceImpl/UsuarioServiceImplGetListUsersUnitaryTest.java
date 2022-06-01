package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.UsuarioServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.UsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.UsuarioMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.UsuarioServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Usuario;
import com.kreitek.PFBKreitekfy.Domain.Persistence.UsuarioPersistence;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsuarioServiceImplGetListUsersUnitaryTest {

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
