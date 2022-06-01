package com.kreitek.PFBKreitekfy.Application.Service.Impl;

import java.util.Optional;

import com.kreitek.PFBKreitekfy.Application.Dto.ArtistaDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.ArtistaMapper;
import com.kreitek.PFBKreitekfy.Application.Service.ArtistaService;
import com.kreitek.PFBKreitekfy.Domain.Entity.Artista;
import com.kreitek.PFBKreitekfy.Domain.Persistence.ArtistaPersistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArtistaServiceImpl implements ArtistaService {
    private final ArtistaPersistence persistence;
    private final ArtistaMapper mapper;

    @Autowired
    public ArtistaServiceImpl(ArtistaPersistence persistence, ArtistaMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArtistaDTO> getArtistasByCriteriaString(Pageable pageable, String filter) {
        Page<Artista> artistaPage = this.persistence.findAll(pageable, filter);
        return artistaPage.map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArtistaDTO> getArtistaById(Long idArtista) {
        return this.persistence.getArtistaById(idArtista).map(mapper::toDto);
    }

    @Override
    @Transactional
    public ArtistaDTO saveArtista(ArtistaDTO artistaDTO) {
        Artista artista = this.persistence.saveArtista(mapper.toEntity(artistaDTO));
        return this.mapper.toDto(artista);
    }

    @Override
    @Transactional
    public void deleteArtistaById(Long artistaId) {
        this.persistence.deleteArtistaById(artistaId);
    }
}
