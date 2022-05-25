package com.kreitek.PFBKreitekfy.Application.Mapper;

import com.kreitek.PFBKreitekfy.Application.Dto.ArtistaDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.EstiloDTO;
import com.kreitek.PFBKreitekfy.Domain.Entity.Estilo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstiloMapper extends EntityMapper<EstiloDTO, Estilo> {

    default Estilo fromId(Long id){
        if(id == null) return null;
        Estilo estilo = new Estilo();
        estilo.setId(id);
        return estilo;
    }

    @Mapping(target ="canciones", ignore = true)
    Estilo toEntity(EstiloDTO dto);
    EstiloDTO toDto(Estilo entity);
}
