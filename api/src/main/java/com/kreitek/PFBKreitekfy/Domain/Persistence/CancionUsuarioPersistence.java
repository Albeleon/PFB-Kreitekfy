package com.kreitek.PFBKreitekfy.Domain.Persistence;


import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;

import java.util.Optional;

public interface CancionUsuarioPersistence {

    Optional<CancionUsuario> findCancionUsuarioByCancionIdAndUsuarioId(Long cancionId, Long usuarioId);

    CancionUsuario saveItem(CancionUsuario entity);

}
