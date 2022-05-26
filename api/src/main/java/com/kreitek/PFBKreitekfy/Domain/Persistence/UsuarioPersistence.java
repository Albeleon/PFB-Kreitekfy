package com.kreitek.PFBKreitekfy.Domain.Persistence;

import com.kreitek.PFBKreitekfy.Domain.Entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioPersistence {

    List<Usuario> getAllUsers();

    Optional<Usuario> getUsuarioById(Long usuarioId);
}
