package com.kreitek.PFBKreitekfy.Application.Mapper;


import com.kreitek.PFBKreitekfy.Application.Dto.CancionDTO;
import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses ={ArtistaMapper.class , AlbumMapper.class , EstiloMapper.class})
public interface CancionMapper extends EntityMapper<CancionDTO, Cancion> {

    @Override
    @Mapping(source = "artistaId", target = "artista")
    @Mapping(source = "albumId", target = "album")
    @Mapping(source = "estiloId", target = "estilo")
    Cancion toEntity(CancionDTO dto);

    @Override
    @Mapping(source = "artista.id", target = "artistaId")
    @Mapping(source = "artista.nombre", target = "artistaNombre")
    @Mapping(source = "album.id", target = "albumId")
    @Mapping(source = "album.nombre", target = "albumNombre")
    @Mapping(source = "album.imagen", target = "albumImagen")
    @Mapping(source = "estilo.id", target = "estiloId")
    @Mapping(source = "estilo.nombre", target = "estiloNombre")
    CancionDTO toDto(Cancion entity);
}
