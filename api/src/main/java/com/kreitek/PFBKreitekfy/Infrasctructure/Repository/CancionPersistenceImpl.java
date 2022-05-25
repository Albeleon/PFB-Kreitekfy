package com.kreitek.PFBKreitekfy.Infrasctructure.Repository;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.CancionSpecification;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.Shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CancionPersistenceImpl implements CancionPersistence {
    private final CancionRepository cancionRepository;

    @Autowired
    public CancionPersistenceImpl(CancionRepository cancionRepository) {
        this.cancionRepository = cancionRepository;
    }

    @Override
    public Page<Cancion> findAll(Pageable pageable, String filter) {
        CancionSpecification specification = new CancionSpecification(SearchCriteriaHelper.fromFilterString(filter));
        return this.cancionRepository.findAll(specification, pageable);
    }
}
