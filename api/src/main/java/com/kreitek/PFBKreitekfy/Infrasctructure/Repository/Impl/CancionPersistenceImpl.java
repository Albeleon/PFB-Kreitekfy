package com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl;

import java.util.List;
import java.util.Optional;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.CancionRepository;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.CancionUsuarioRepository;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.CancionSpecification;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.Shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class CancionPersistenceImpl implements CancionPersistence {
    private final CancionRepository cancionRepository;
    private final CancionUsuarioRepository cancionUsuarioRepository;

    @Autowired
    public CancionPersistenceImpl(
        CancionRepository cancionRepository,
        CancionUsuarioRepository cancionUsuarioRepository)
    {
        this.cancionRepository = cancionRepository;
        this.cancionUsuarioRepository = cancionUsuarioRepository;
    }

    @Override
    public List<Cancion> findAll(String filter) {
        CancionSpecification specification = new CancionSpecification(SearchCriteriaHelper.fromFilterString(filter));
        return this.cancionRepository.findAll(specification);
    }

    @Override
    public Page<Cancion> findAll(Pageable pageable, String filter) {
        CancionSpecification specification = new CancionSpecification(SearchCriteriaHelper.fromFilterString(filter));
        return this.cancionRepository.findAll(specification, pageable);
    }

    @Override
    public Cancion saveItem(Cancion entity) {
        return this.cancionRepository.save(entity);
    }

    @Override
    public Optional<Cancion> getCancionById(Long idCancion) {
        return this.cancionRepository.findById(idCancion);
    }

    @Override
    public Optional<CancionUsuario> getCancionUsuarioById(Long idCancion, Long idUsuario) {
        return this.cancionUsuarioRepository.findByCancion_idAndUsuario_Id(idCancion, idUsuario);
    }
}
