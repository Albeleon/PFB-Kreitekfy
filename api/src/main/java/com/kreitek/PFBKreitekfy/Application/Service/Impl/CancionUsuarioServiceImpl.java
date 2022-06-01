package com.kreitek.PFBKreitekfy.Application.Service.Impl;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.CancionUsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionUsuarioMapper;
import com.kreitek.PFBKreitekfy.Application.Service.CancionService;
import com.kreitek.PFBKreitekfy.Application.Service.CancionUsuarioService;
import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionPersistence;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionUsuarioPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CancionUsuarioServiceImpl implements CancionUsuarioService {

    private final CancionUsuarioPersistence cancionUsuarioPersistence;
    private final CancionService cancionService;
    private final CancionUsuarioMapper cancionUsuarioMapper;
    private final CancionMapper cancionMapper;
    private final CancionPersistence cancionPersistence;

    @Autowired
    public CancionUsuarioServiceImpl(
            CancionUsuarioPersistence cancionUsuarioPersistence,
            CancionService cancionService,
            CancionUsuarioMapper cancionUsuarioMapper,
            CancionMapper cancionMapper,
            CancionPersistence cancionPersistence) {
        this.cancionUsuarioPersistence = cancionUsuarioPersistence;
        this.cancionService = cancionService;
        this.cancionUsuarioMapper = cancionUsuarioMapper;
        this.cancionMapper = cancionMapper;
        this.cancionPersistence = cancionPersistence;
    }

    @Override
    @Transactional
    public CancionUsuarioDTO updateReproduccion(CancionUsuarioDTO cancionUsuarioDTO) {
        cancionService.updateReproduccionCancion(cancionUsuarioDTO.getCancionId());
        Optional<CancionUsuario> cancionUsuario = cancionUsuarioPersistence.findCancionUsuarioByCancionIdAndUsuarioId(cancionUsuarioDTO.getCancionId(), cancionUsuarioDTO.getUsuarioId());
        if (cancionUsuario.isPresent()) {
            cancionUsuario.get().setReproducciones(cancionUsuario.get().getReproducciones() + 1);
            cancionUsuarioPersistence.saveItem(cancionUsuario.get());
            return cancionUsuarioMapper.toDto(cancionUsuario.get());
        } else {
            CancionUsuario cancionUsuarioUpdated = cancionUsuarioMapper.toEntity(cancionUsuarioDTO);
            cancionUsuarioUpdated.setReproducciones(1L);
            cancionUsuarioUpdated = cancionUsuarioPersistence.saveItem(cancionUsuarioUpdated);
            return cancionUsuarioMapper.toDto(cancionUsuarioUpdated);
        }
    }

    @Override
    @Transactional
    public CancionUsuarioDTO updateValoracion(CancionUsuarioDTO cancionUsuarioDTO) {
        CancionUsuario cancionUsuarioUpdated;
        Optional<CancionUsuario> cancionUsuario = cancionUsuarioPersistence.findCancionUsuarioByCancionIdAndUsuarioId(cancionUsuarioDTO.getCancionId(), cancionUsuarioDTO.getUsuarioId());
        if (cancionUsuario.isPresent()) {
            cancionUsuario.get().setValoracion(cancionUsuarioDTO.getValoracion());
            cancionUsuarioUpdated = cancionUsuarioPersistence.saveItem(cancionUsuario.get());
        } else {
            cancionUsuarioUpdated = cancionUsuarioMapper.toEntity(cancionUsuarioDTO);
            cancionUsuarioUpdated.setValoracion(cancionUsuarioDTO.getValoracion());
            cancionUsuarioPersistence.saveItem(cancionUsuarioUpdated);
        }

        Optional<Cancion> cancion = cancionPersistence.getCancionById(cancionUsuarioUpdated.getId().getCancionId()) ;

        if (cancion.isPresent()) {
            cancion.get().setValoracionMedia(this.calculateValoracionMedia(cancionUsuarioUpdated, cancion.get()));
            cancionPersistence.saveItem(cancion.get());
        }


        return cancionUsuarioMapper.toDto(cancionUsuarioUpdated);
    }

    private Float calculateValoracionMedia(CancionUsuario cancionUsuarioUpdated, Cancion cancion) {
        Long numeroUsuarios = cancionUsuarioPersistence.findNumeroUsuariosValoracion(cancion.getId());

        Long valoracionUsuario = cancionUsuarioUpdated.getValoracion();
        Float valoracionAntigua = cancion.getValoracionMedia() != null ? cancion.getValoracionMedia() : 0f;

        Float valoracionFinal = valoracionAntigua + (valoracionUsuario - valoracionAntigua) / numeroUsuarios;
        return valoracionFinal;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CancionUsuarioDTO> getCancionUsuarioById(Long idCancion, Long idUsuario) {
        return cancionUsuarioPersistence.findCancionUsuarioByCancionIdAndUsuarioId(idCancion, idUsuario).map(cancionUsuarioMapper::toDto);
    }
}
