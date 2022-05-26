package com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl;

import com.kreitek.PFBKreitekfy.Domain.Entity.Estilo;
import com.kreitek.PFBKreitekfy.Domain.Persistence.EstiloPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.EstiloRepository;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.EstiloSpecification;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.Shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class EstiloPersistenceImpl implements EstiloPersistence {
    private final EstiloRepository estiloRepository;

    @Autowired
    public EstiloPersistenceImpl(EstiloRepository estiloRepository) {
        this.estiloRepository = estiloRepository;
    }

    @Override
    public Page<Estilo> findAll(Pageable pageable, String filter) {
        EstiloSpecification specification = new EstiloSpecification(SearchCriteriaHelper.fromFilterString(filter));
        return this.estiloRepository.findAll(specification, pageable);
    }
}
