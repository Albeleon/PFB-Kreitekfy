package com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl;

import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionUsuarioPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.CancionUsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CancionUsuarioPersistenceImpl implements CancionUsuarioPersistence {

    private final CancionUsuarioRepository cancionUsuarioRepository;

    public CancionUsuarioPersistenceImpl(CancionUsuarioRepository cancionUsuarioRepository) {
        this.cancionUsuarioRepository = cancionUsuarioRepository;
    }

    @Override
    public Optional<CancionUsuario> findCancionUsuarioByCancionIdAndUsuarioId(Long cancionId, Long usuarioId) {
        return cancionUsuarioRepository.findByCancion_idAndUsuario_Id(cancionId, usuarioId);
    }

    @Override
    public CancionUsuario saveItem(CancionUsuario entity) {
        return cancionUsuarioRepository.save(entity);
    }
}
