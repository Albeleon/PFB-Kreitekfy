package com.kreitek.PFBKreitekfy.Application.Mapper;

import com.kreitek.PFBKreitekfy.Application.Dto.AlbumDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.EstiloDTO;
import com.kreitek.PFBKreitekfy.Domain.Entity.Album;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlbumMapper extends EntityMapper<AlbumDTO, Album> {

    default Album fromId(Long id){
        if(id == null) return null;
        Album album = new Album();
        album.setId(id);
        return album;
    }

    @Mapping(target ="canciones", ignore = true)
    Album toEntity(AlbumDTO dto);
    AlbumDTO toDto(Album entity);
}
