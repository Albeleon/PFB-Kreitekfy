package com.kreitek.PFBKreitekfy.Application.Service;

import java.util.List;
import java.util.Optional;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionSimpleDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.EstiloDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstiloService {

    Page<EstiloDTO> getEstilosByCriteriaString(Pageable pageable, String filter);

	Optional<EstiloDTO> getEstiloById(Long idEstilo);

    List<CancionSimpleDTO> getCancionesRecomendadas(Long usuarioId);
}