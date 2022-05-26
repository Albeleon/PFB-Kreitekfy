package com.kreitek.PFBKreitekfy.Application.Service;

import java.util.Optional;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.CancionSimpleDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.CancionUsuarioDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CancionService {

    Page<CancionSimpleDTO> getCancionesByCriteriaString(Pageable pageable, String filter);

    CancionDTO saveItem(CancionDTO cancionDTO);

    Optional<CancionDTO> getCancionById(Long idCancion);

    Optional<CancionUsuarioDTO> getCancionUsuarioById(Long idCancion, Long idUsuario);
}
