package com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl;

import java.util.Optional;

import com.kreitek.PFBKreitekfy.Domain.Entity.Artista;
import com.kreitek.PFBKreitekfy.Domain.Persistence.ArtistaPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.ArtistaRepository;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.ArtistaSpecification;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.Shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistaPersistenceImpl implements ArtistaPersistence {
    private final ArtistaRepository artistaRepository;

    @Autowired
    public ArtistaPersistenceImpl(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    @Override
    public Page<Artista> findAll(Pageable pageable, String filter) {
        ArtistaSpecification specification = new ArtistaSpecification(SearchCriteriaHelper.fromFilterString(filter));
        return this.artistaRepository.findAll(specification, pageable);
    }

    @Override
    public Optional<Artista> getArtistaById(Long idArtista) {
        return this.artistaRepository.findById(idArtista);
    }

    @Override
    public Artista saveArtista(Artista artista) {
        return this.artistaRepository.save(artista);
    }

    @Override
    public void deleteArtistaById(Long artistaId) {
        this.artistaRepository.deleteById(artistaId);
    }
}
