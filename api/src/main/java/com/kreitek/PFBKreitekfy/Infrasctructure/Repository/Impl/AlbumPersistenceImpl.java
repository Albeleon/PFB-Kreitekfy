package com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Impl;

import java.util.Optional;

import com.kreitek.PFBKreitekfy.Domain.Entity.Album;
import com.kreitek.PFBKreitekfy.Domain.Persistence.AlbumPersistence;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.AlbumRepository;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.AlbumSpecification;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.Shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumPersistenceImpl implements AlbumPersistence {
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumPersistenceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Page<Album> findAll(Pageable pageable, String filter) {
        AlbumSpecification specification = new AlbumSpecification(SearchCriteriaHelper.fromFilterString(filter));
        return this.albumRepository.findAll(specification, pageable);
    }

    @Override
    public Optional<Album> getAlbumById(Long idAlbum) {
        return this.albumRepository.findById(idAlbum);
    }

    @Override
    public void deleteAlbumById(Long albumId) {
        this.albumRepository.deleteById(albumId);
    }

    @Override
    public Album saveItem(Album entity) {
        return this.albumRepository.save(entity);
    }
}
