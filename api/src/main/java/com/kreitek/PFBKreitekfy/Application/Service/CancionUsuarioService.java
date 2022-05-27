package com.kreitek.PFBKreitekfy.Application.Service;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionUsuarioDTO;

import java.util.Optional;

public interface CancionUsuarioService {

    CancionUsuarioDTO updateReproduccion(CancionUsuarioDTO cancionUsuarioDTO);

    CancionUsuarioDTO updateValoracion(CancionUsuarioDTO cancionUsuarioDTO);

    Optional<CancionUsuarioDTO> getCancionUsuarioById(Long idCancion, Long idUsuario);
}
