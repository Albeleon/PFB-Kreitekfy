package com.kreitek.PFBKreitekfy.Domain.Persistence;


import java.util.Optional;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CancionPersistence {
    Page<Cancion> findAll(Pageable pageable, String filter);

    Cancion saveItem(Cancion entity);

    Optional<Cancion> getCancionById(Long idCancion);
}
