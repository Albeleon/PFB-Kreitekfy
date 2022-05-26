package com.kreitek.PFBKreitekfy.Application.Service;

import java.util.Optional;

import com.kreitek.PFBKreitekfy.Application.Dto.AlbumDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlbumService {

    Page<AlbumDTO> getAlbumsByCriteriaString(Pageable pageable, String filter);

    Optional<AlbumDTO> getAlbumById(Long idAlbum);
}