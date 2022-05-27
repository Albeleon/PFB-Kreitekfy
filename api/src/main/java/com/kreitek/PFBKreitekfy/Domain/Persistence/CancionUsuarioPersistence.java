package com.kreitek.PFBKreitekfy.Domain.Persistence;


import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;

import java.util.Optional;

public interface CancionUsuarioPersistence {

    CancionUsuario updateReproduccion(CancionUsuario cancionUsuario);

    Optional<CancionUsuario> findCancionUsuarioByCancionIdAndUsuarioId(Long cancionId, Long usuarioId);

}
