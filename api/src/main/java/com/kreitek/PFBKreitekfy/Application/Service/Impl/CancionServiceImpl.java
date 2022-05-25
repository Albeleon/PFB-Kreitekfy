package com.kreitek.PFBKreitekfy.Application.Service.Impl;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionSimpleDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionSimpleMapper;
import com.kreitek.PFBKreitekfy.Application.Service.CancionService;
import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancionServiceImpl implements CancionService {
    private final CancionPersistence persistence;
    private final CancionSimpleMapper mapper;

    @Autowired
    public CancionServiceImpl(CancionPersistence persistence, CancionSimpleMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public Page<CancionSimpleDTO> getCancionesByCriteriaString(Pageable pageable, String filter) {
        Page<Cancion> cancionPage = this.persistence.findAll(pageable, filter);
        return cancionPage.map(mapper::toDto);
    }
}
