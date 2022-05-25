package com.kreitek.PFBKreitekfy.Application.Service.Impl;

import com.kreitek.PFBKreitekfy.Application.Dto.UsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Mapper.UsuarioMapper;
import com.kreitek.PFBKreitekfy.Application.Service.UsuarioService;
import com.kreitek.PFBKreitekfy.Domain.Entity.Usuario;
import com.kreitek.PFBKreitekfy.Domain.Persistence.UsuarioPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioMapper usuarioMapper;

    private final UsuarioPersistence usuarioPersistence;

    @Autowired
    public UsuarioServiceImpl(UsuarioMapper usuarioMapper, UsuarioPersistence usuarioPersistence) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioPersistence = usuarioPersistence;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> getListUsers() {
        List<Usuario> usuarios = this.usuarioPersistence.getAllUsers();
        return usuarioMapper.toListDto(usuarios);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> getUsuarioById(Long id) {
        return usuarioPersistence.getUsuarioById(id).map(usuarioMapper::toDto);
    }


}
