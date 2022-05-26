package com.kreitek.PFBKreitekfy.Domain.Persistence;


import com.kreitek.PFBKreitekfy.Domain.Entity.Artista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArtistaPersistence {
    Page<Artista> findAll(Pageable pageable, String filter);
}
