package com.kreitek.PFBKreitekfy.UnitaryTests.Application.Service.UsuarioServiceImpl;

import com.kreitek.PFBKreitekfy.Application.Dto.UsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.UsuarioMapperImpl;
import com.kreitek.PFBKreitekfy.Application.Service.Impl.UsuarioServiceImpl;
import com.kreitek.PFBKreitekfy.Domain.Entity.Usuario;
import com.kreitek.PFBKreitekfy.Domain.Persistence.UsuarioPersistence;
import com.kreitek.PFBKreitekfy.PfbKreitekfyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PfbKreitekfyApplication.class })
public class UsuarioServiceImplGetUsuarioByIdUnitaryTest {

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
