package com.kreitek.PFBKreitekfy.Application.Service;

import java.util.List;
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

    void updateReproduccionCancion(Long idCancion);

    List<CancionSimpleDTO> getCancionesMasValoradas(String filter);

    List<CancionSimpleDTO> getCancionesNovedades(String filter);

    List<CancionSimpleDTO> getCancionesMasReproducidas(String filter);
}
