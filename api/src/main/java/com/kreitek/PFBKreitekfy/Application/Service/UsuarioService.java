package com.kreitek.PFBKreitekfy.Application.Service;

import com.kreitek.PFBKreitekfy.Application.Dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioDTO> getListUsers();

    Optional<UsuarioDTO> getUsuarioById(Long id);
}
