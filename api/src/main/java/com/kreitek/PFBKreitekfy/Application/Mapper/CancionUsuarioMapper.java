package com.kreitek.PFBKreitekfy.Application.Mapper;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionUsuarioDTO;
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import com.kreitek.PFBKreitekfy.Domain.Key.CancionUsuarioKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class , CancionMapper.class})
public interface CancionUsuarioMapper extends EntityMapper<CancionUsuarioDTO, CancionUsuario> {

    default CancionUsuario fromId(CancionUsuarioKey id){
        if(id == null) return null;
        CancionUsuario cancionUsuario = new CancionUsuario();
        cancionUsuario.setId(id);
        return cancionUsuario;
    }

    //@Mapping(source = "cancionId" , target ="cancion")
    @Mapping(source = "cancionId" , target ="id.cancionId")
    //Mapping(source = "usuarioId" , target ="usuario")
    @Mapping(source = "usuarioId" , target ="id.usuarioId")
    CancionUsuario toEntity(CancionUsuarioDTO dto);


    @Override
    @Mapping(source = "cancion.id", target = "cancionId")
    @Mapping(source = "usuario.id", target = "usuarioId")
    CancionUsuarioDTO toDto(CancionUsuario entity);
}
