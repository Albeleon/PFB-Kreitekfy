package com.kreitek.PFBKreitekfy.Infrasctructure.Rest;


import com.kreitek.PFBKreitekfy.Application.Dto.UsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @CrossOrigin
    @GetMapping(value = "/usuarios", produces = "application/json")
    public ResponseEntity<List<UsuarioDTO>> getAllUsers() {
        return new ResponseEntity<>(this.usuarioService.getListUsers(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/usuarios/{usuarioId}", produces = "application/json")
    public ResponseEntity<UsuarioDTO> getUserById(@PathVariable Long usuarioId) {
        return usuarioService.getUsuarioById(usuarioId)
                .map(usuarioDTO -> new ResponseEntity<>(usuarioDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
