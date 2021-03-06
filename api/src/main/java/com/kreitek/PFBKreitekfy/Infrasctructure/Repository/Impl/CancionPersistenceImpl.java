package com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl;

import java.util.List;
import java.util.Optional;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.CancionRepository;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.CancionSpecification;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.Shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class CancionPersistenceImpl implements CancionPersistence {
    private final CancionRepository cancionRepository;

    @Autowired
    public CancionPersistenceImpl(
            CancionRepository cancionRepository) {
        this.cancionRepository = cancionRepository;
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
    public void deleteCancionById(Long cancionId) {
        this.cancionRepository.deleteById(cancionId);
    }

    @Override
    public List<Cancion> find5CancionesRecomendadas(Long usuarioId) {
        return this.cancionRepository.find5CancionesRecomendadas(usuarioId);
    }
}
