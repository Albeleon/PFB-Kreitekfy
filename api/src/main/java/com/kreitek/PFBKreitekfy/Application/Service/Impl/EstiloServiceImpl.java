package com.kreitek.PFBKreitekfy.Application.Service.Impl;

import java.util.Optional;

import com.kreitek.PFBKreitekfy.Application.Dto.EstiloDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.EstiloMapper;
import com.kreitek.PFBKreitekfy.Application.Service.EstiloService;
import com.kreitek.PFBKreitekfy.Domain.Entity.Estilo;
import com.kreitek.PFBKreitekfy.Domain.Persistence.EstiloPersistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstiloServiceImpl implements EstiloService {
    private final EstiloPersistence persistence;
    private final EstiloMapper mapper;

    @Autowired
    public EstiloServiceImpl(
        EstiloPersistence persistence,
        EstiloMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EstiloDTO> getEstilosByCriteriaString(Pageable pageable, String filter) {
        Page<Estilo> estiloPage = this.persistence.findAll(pageable, filter);
        return estiloPage.map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EstiloDTO> getEstiloById(Long idEstilo) {
        return this.persistence.getEstiloById(idEstilo).map(mapper::toDto);
    }
}