package com.kreitek.PFBKreitekfy.Application.Service.Impl;

import java.util.Optional;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.CancionSimpleDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.CancionUsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionSimpleMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionUsuarioMapper;
import com.kreitek.PFBKreitekfy.Application.Service.CancionService;
import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CancionServiceImpl implements CancionService {
    private final CancionPersistence persistence;
    private final CancionUsuarioMapper cancionUsuarioMapper;
    private final CancionSimpleMapper simpleMapper;
    private final CancionMapper mapper;

    @Autowired
    public CancionServiceImpl(
            CancionPersistence persistence,
            CancionUsuarioMapper cancionUsuarioMapper,
            CancionSimpleMapper simpleMapper,
            CancionMapper mapper) {
        this.persistence = persistence;
        this.cancionUsuarioMapper = cancionUsuarioMapper;
        this.simpleMapper = simpleMapper;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CancionSimpleDTO> getCancionesByCriteriaString(Pageable pageable, String filter) {
        Page<Cancion> cancionPage = this.persistence.findAll(pageable, filter);
        return cancionPage.map(simpleMapper::toDto);
    }

    @Override
    @Transactional()
    public CancionDTO saveItem(CancionDTO cancionDTO) {
        Cancion cancionSaved = this.persistence.saveItem(this.mapper.toEntity(cancionDTO));
        return this.mapper.toDto(cancionSaved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CancionDTO> getCancionById(Long idCancion) {
        return this.persistence.getCancionById(idCancion).map(mapper::toDto);
    }

    @Override
    @Transactional
    public void updateReproduccionCancion(Long idCancion) {
        CancionDTO cancionDTO = this.getCancionById(idCancion).orElseThrow(() -> new RuntimeException("No existe este Usuario"));
        Cancion cancion = mapper.toEntity(cancionDTO);
        cancion.setReproduccion(cancion.getReproduccion() + 1);
        persistence.saveItem(cancion);
    }
}
