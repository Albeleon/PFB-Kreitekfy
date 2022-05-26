package com.kreitek.PFBKreitekfy.Application.Service;

import com.kreitek.PFBKreitekfy.Application.Dto.ArtistaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArtistaService {

    Page<ArtistaDTO> getArtistasByCriteriaString(Pageable pageable, String filter);
}