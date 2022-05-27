package com.kreitek.PFBKreitekfy.Application.Service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.kreitek.PFBKreitekfy.Application.Comparer.ValoracionComparer;
import com.kreitek.PFBKreitekfy.Application.Dto.CancionDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.CancionSimpleDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionSimpleMapper;
import com.kreitek.PFBKreitekfy.Application.Service.CancionService;
import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Persistence.CancionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CancionServiceImpl implements CancionService {
    private final CancionPersistence persistence;
    private final CancionSimpleMapper simpleMapper;
    private final CancionMapper mapper;

    @Autowired
    public CancionServiceImpl(
            CancionPersistence persistence,
            CancionSimpleMapper simpleMapper,
            CancionMapper mapper) {
        this.persistence = persistence;
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


    @Override
    @Transactional(readOnly = true)
    public List<CancionSimpleDTO> getCancionesNovedades(String filter) {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("fecha").descending());
        List<Cancion> canciones = this.persistence.findAll(pageable, filter).getContent();
        return simpleMapper.toListDto(canciones);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CancionSimpleDTO> getCancionesMasReproducidas(String filter) {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("reproduccion").descending());
        List<Cancion> canciones = this.persistence.findAll(pageable, filter).getContent();
        return simpleMapper.toListDto(canciones);
    }

    @Override
    public void deleteCancionById(Long cancionId) {
        this.persistence.deleteCancionById(cancionId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CancionSimpleDTO> getCancionesMasValoradas(String filter) {
        List<Cancion> canciones = this.persistence.findAll(filter);
        Collections.sort(canciones, new ValoracionComparer());
        while (canciones.size() > 5) {
            canciones.remove(canciones.size() - 1);
        }
        return simpleMapper.toListDto(canciones);
    }
}
