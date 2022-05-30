package com.kreitek.PFBKreitekfy.Application.Service.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.kreitek.PFBKreitekfy.Application.Comparer.ReproduccionesComparer;
import com.kreitek.PFBKreitekfy.Application.Comparer.ReproduccionesUsuarioComparer;
import com.kreitek.PFBKreitekfy.Application.Dto.CancionSimpleDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.EstiloDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.CancionSimpleMapper;
import com.kreitek.PFBKreitekfy.Application.Mapper.EstiloMapper;
import com.kreitek.PFBKreitekfy.Application.Service.EstiloService;
import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
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
    private final CancionSimpleMapper cancionSimpleMapper;

    @Autowired
    public EstiloServiceImpl(
        EstiloPersistence persistence,
        EstiloMapper mapper,
        CancionSimpleMapper cancionSimpleMapper) {
        this.persistence = persistence;
        this.mapper = mapper;
        this.cancionSimpleMapper = cancionSimpleMapper;
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

    @Override
    @Transactional
    public EstiloDTO saveEstilo(EstiloDTO estiloDTO) {
        Estilo estilo = this.persistence.saveEstilo(mapper.toEntity(estiloDTO));
        return this.mapper.toDto(estilo);
    }

    @Override
    @Transactional
    public void deleteEstiloById(Long estiloId) {
        this.persistence.deleteEstiloById(estiloId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CancionSimpleDTO> getCancionesRecomendadas(Long usuarioId) {
        List<Estilo> estilos = getEstilosOrdenReproducccionesUsuario(usuarioId);

        while (estilos.size() > 2) {
            estilos.remove(estilos.size() - 1);
        }

        List<Cancion> canciones = this.getCancionesDeEstilos(estilos);
        canciones = this.getCancionesConValoracionMasDe3(canciones);
        Collections.sort(canciones, new ReproduccionesComparer());

        while (canciones.size() > 5) {
            canciones.remove(canciones.size() - 1);
        }

        return cancionSimpleMapper.toListDto(canciones);
    }

    private List<Estilo> getEstilosOrdenReproducccionesUsuario(Long usuarioId) {
        List<Estilo> estilos = this.persistence.findAll();
        Collections.sort(estilos, new ReproduccionesUsuarioComparer(usuarioId));
        return estilos;
    }

    private List<Cancion> getCancionesDeEstilos(List<Estilo> estilos) {
        List<Cancion> canciones = new ArrayList<>();
        Iterator<Estilo> iteratorEstilos = estilos.iterator();

        while (iteratorEstilos.hasNext()) {
            Iterator<Cancion> cancionesIterator = iteratorEstilos.next().getCanciones().iterator();
            while (cancionesIterator.hasNext()) {
                canciones.add(cancionesIterator.next());
            }
        }

        return canciones;
    }

    private List<Cancion> getCancionesConValoracionMasDe3 (List<Cancion> canciones) {
        List<Cancion> filteredCanciones = new ArrayList<>();
        Iterator<Cancion> iteratorCanciones = canciones.iterator();

        while (iteratorCanciones.hasNext()) {
            Cancion cancion = iteratorCanciones.next();
            float valoracion = getValoracionMedia(cancion);
            if (valoracion >= 3) {
                filteredCanciones.add(cancion);
            }
        }

        return filteredCanciones;
    }

    private float getValoracionMedia(Cancion cancion) {
        Iterator<CancionUsuario> cancionesUsuario = cancion.getCancionesUsuarios().iterator();
        Float valoracion = 0f;
        int numeroUsuarios = 0;

        while (cancionesUsuario.hasNext()) {
            CancionUsuario cancionUsuario = cancionesUsuario.next();
            if (cancionUsuario.getValoracion() != null) {
                valoracion += cancionUsuario.getValoracion();
                numeroUsuarios++;
            }
        }
        
        return numeroUsuarios > 0 ? valoracion / numeroUsuarios : 0;
    }
}