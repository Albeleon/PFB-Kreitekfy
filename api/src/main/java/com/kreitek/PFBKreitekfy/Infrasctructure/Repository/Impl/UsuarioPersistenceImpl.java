package com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl;

import com.kreitek.PFBKreitekfy.Domain.Entity.Usuario;
import com.kreitek.PFBKreitekfy.Domain.Persistence.UsuarioPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioPersistenceImpl implements UsuarioPersistence {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioPersistenceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long usuarioId) {
        return usuarioRepository.findById(usuarioId);
    }
}
