package com.kreitek.PFBKreitekfy.Domain.Persistence;


import java.util.List;
import java.util.Optional;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CancionPersistence {
    List<Cancion> findAll(String filter);

    Page<Cancion> findAll(Pageable pageable, String filter);

    Cancion saveItem(Cancion entity);

    Optional<Cancion> getCancionById(Long idCancion);

    Optional<CancionUsuario> getCancionUsuarioById(Long idCancion, Long idUsuario);
}
