package com.kreitek.PFBKreitekfy.Domain.Persistence;


import com.kreitek.PFBKreitekfy.Domain.Entity.Estilo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstiloPersistence {
    Page<Estilo> findAll(Pageable pageable, String filter);
}
