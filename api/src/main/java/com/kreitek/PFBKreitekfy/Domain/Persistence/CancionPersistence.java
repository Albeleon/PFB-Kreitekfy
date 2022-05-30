package com.kreitek.PFBKreitekfy.Domain.Persistence;


import java.util.List;
import java.util.Optional;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CancionPersistence {
    List<Cancion> findAll(String filter);

    Page<Cancion> findAll(Pageable pageable, String filter);

    Cancion saveItem(Cancion entity);

    Optional<Cancion> getCancionById(Long idCancion);

    void deleteCancionById(Long cancionId);

    List<Cancion> find5CancionesMasValoradas(Pageable pageable);

    List<Cancion> find5CancionesRecomendadas(Long usuarioId);

}
