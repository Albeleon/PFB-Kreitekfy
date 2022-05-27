package com.kreitek.PFBKreitekfy.Domain.Persistence;


import java.util.List;
import java.util.Optional;

import com.kreitek.PFBKreitekfy.Domain.Entity.Estilo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstiloPersistence {
    Page<Estilo> findAll(Pageable pageable, String filter);

    Optional<Estilo> getEstiloById(Long idEstilo);

    List<Estilo> findAll();
}
