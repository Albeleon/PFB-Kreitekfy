package com.kreitek.PFBKreitekfy.Infrasctructure.Rest;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionUsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Service.CancionUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CancionUsuarioRestController {

    private final CancionUsuarioService cancionUsuarioService;


    public CancionUsuarioRestController(CancionUsuarioService cancionUsuarioService) {
        this.cancionUsuarioService = cancionUsuarioService;
    }

    @PutMapping(value = "/canciones/{cancionId}/usuarios/{usuarioId}/reproduccion", produces = "application/json")
    public ResponseEntity<CancionUsuarioDTO> updateReproduccion(@RequestBody CancionUsuarioDTO cancionUsuarioDTO) {
        return new ResponseEntity<>(cancionUsuarioService.updateReproduccion(cancionUsuarioDTO), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/canciones/{idCancion}/usuarios/{idUsuario}", produces = "application/json")
    ResponseEntity<CancionUsuarioDTO> getCancionUsuario(@PathVariable Long idCancion, @PathVariable Long idUsuario) {
        return cancionUsuarioService.getCancionUsuarioById(idCancion, idUsuario)
                .map(cancionUsuarioDTO -> new ResponseEntity<>(cancionUsuarioDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
