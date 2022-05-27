package com.kreitek.PFBKreitekfy.Application.Service.Impl;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionUsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionUsuarioMapper;
import com.kreitek.PFBKreitekfy.Application.Service.CancionService;
import com.kreitek.PFBKreitekfy.Application.Service.CancionUsuarioService;
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionUsuarioPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CancionUsuarioServiceImpl implements CancionUsuarioService {

    private final CancionUsuarioPersistence cancionUsuarioPersistence;
    private final CancionService cancionService;
    private final CancionUsuarioMapper cancionUsuarioMapper;

    @Autowired
    public CancionUsuarioServiceImpl(CancionUsuarioPersistence cancionUsuarioPersistence, CancionService cancionService, CancionUsuarioMapper cancionUsuarioMapper) {
        this.cancionUsuarioPersistence = cancionUsuarioPersistence;
        this.cancionService = cancionService;
        this.cancionUsuarioMapper = cancionUsuarioMapper;
    }

    @Override
    @Transactional
    public CancionUsuarioDTO updateReproduccion(CancionUsuarioDTO cancionUsuarioDTO) {
        cancionService.updateReproduccionCancion(cancionUsuarioDTO.getCancionId());
        CancionUsuario cancionUsuario = cancionUsuarioPersistence.updateReproduccion(cancionUsuarioMapper.toEntity(cancionUsuarioDTO));
        return cancionUsuarioMapper.toDto(cancionUsuario);
    }

    @Override
    public CancionUsuarioDTO updateValoracion(CancionUsuarioDTO cancionUsuarioDTO) {
        return cancionUsuarioDTO;
    }
}
