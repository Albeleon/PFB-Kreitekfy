package com.kreitek.PFBKreitekfy.Application.Mapper;

import com.kreitek.PFBKreitekfy.Application.Dto.UsuarioDTO;
import com.kreitek.PFBKreitekfy.Domain.Entity.Usuario;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring" , uses = {CancionUsuarioMapper.class})
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {
    default Usuario fromId(Long id){
        if(id == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }

}
