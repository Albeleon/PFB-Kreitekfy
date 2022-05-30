package com.kreitek.PFBKreitekfy.Application.Service.Impl;

import java.util.Optional;

import com.kreitek.PFBKreitekfy.Application.Dto.AlbumDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.AlbumMapper;
import com.kreitek.PFBKreitekfy.Application.Service.AlbumService;
import com.kreitek.PFBKreitekfy.Domain.Entity.Album;
import com.kreitek.PFBKreitekfy.Domain.Persistence.AlbumPersistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumPersistence persistence;
    private final AlbumMapper mapper;

    @Autowired
    public AlbumServiceImpl(AlbumPersistence persistence, AlbumMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlbumDTO> getAlbumsByCriteriaString(Pageable pageable, String filter) {
        Page<Album> albumPage = this.persistence.findAll(pageable, filter);
        return albumPage.map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlbumDTO> getAlbumById(Long idAlbum) {
        return this.persistence.getAlbumById(idAlbum).map(mapper::toDto);
    }

    @Override
    public void deleteCancionById(Long albumId) {
        this.persistence.deleteAlbumById(albumId);
        
    }

    @Override
    @Transactional()
    public AlbumDTO saveItem(AlbumDTO albumDTO) {
        Album albumSaved = this.persistence.saveItem(this.mapper.toEntity(albumDTO));
        return this.mapper.toDto(albumSaved);
    }
}
