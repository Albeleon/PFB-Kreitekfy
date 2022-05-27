package com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl;

import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionUsuarioPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.CancionUsuarioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CancionUsuarioPersistenceImpl implements CancionUsuarioPersistence {

    private final CancionUsuarioRepository cancionUsuarioRepository;

    public CancionUsuarioPersistenceImpl(CancionUsuarioRepository cancionUsuarioRepository) {
        this.cancionUsuarioRepository = cancionUsuarioRepository;
    }

    @Override
    public CancionUsuario updateReproduccion(CancionUsuario cancionUsuario) {
        cancionUsuario.setReproducciones(cancionUsuario.getReproducciones() + 1);
        return cancionUsuarioRepository.save(cancionUsuario);
    }
}
