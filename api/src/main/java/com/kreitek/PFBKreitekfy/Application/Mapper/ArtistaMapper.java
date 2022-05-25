package com.kreitek.PFBKreitekfy.Application.Mapper;

import com.kreitek.PFBKreitekfy.Application.Dto.ArtistaDTO;
import com.kreitek.PFBKreitekfy.Domain.Entity.Artista;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = CancionMapper.class)
public interface ArtistaMapper extends EntityMapper<ArtistaDTO, Artista> {

    default Artista fromId(Long id){
        if(id == null) return null;
        Artista artista = new Artista();
        artista.setId(id);
        return artista;
    }

   @Mapping(target ="canciones", ignore = true)
    Artista toEntity(ArtistaDTO dto);
    ArtistaDTO toDto(Artista entity);
}
