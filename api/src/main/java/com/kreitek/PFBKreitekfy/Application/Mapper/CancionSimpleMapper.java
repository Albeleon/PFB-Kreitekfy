package com.kreitek.PFBKreitekfy.Application.Mapper;


import com.kreitek.PFBKreitekfy.Application.Dto.CancionSimpleDTO;
import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ArtistaMapper.class, AlbumMapper.class})
public interface CancionSimpleMapper extends EntityMapper<CancionSimpleDTO, Cancion> {

    default Cancion fromId(Long id) {
        if (id == null) return null;
        Cancion cancion = new Cancion();
        cancion.setId(id);
        return cancion;
    }

    @Override
    @Mapping(source = "artistaId", target = "artista")
    @Mapping(source = "albumId", target = "album")
    Cancion toEntity(CancionSimpleDTO dto);

    @Override
    @Mapping(source = "artista.id", target = "artistaId")
    @Mapping(source = "artista.nombre", target = "artistaNombre")
    @Mapping(source = "album.id", target = "albumId")
    @Mapping(source = "album.imagen", target = "albumImagen")
    CancionSimpleDTO toDto(Cancion entity);


}
