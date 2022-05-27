package com.kreitek.PFBKreitekfy.Application.Service;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionUsuarioDTO;

public interface CancionUsuarioService {

    CancionUsuarioDTO updateReproduccion(CancionUsuarioDTO cancionUsuarioDTO);
    CancionUsuarioDTO updateValoracion(CancionUsuarioDTO cancionUsuarioDTO);
}
