package com.kreitek.PFBKreitekfy.Domain.Persistence;


import com.kreitek.PFBKreitekfy.Domain.Entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlbumPersistence {
    Page<Album> findAll(Pageable pageable, String filter);
}
