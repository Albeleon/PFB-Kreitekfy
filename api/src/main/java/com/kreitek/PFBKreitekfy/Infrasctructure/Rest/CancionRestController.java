package com.kreitek.PFBKreitekfy.Infrasctructure.Rest;


import java.util.Optional;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.CancionSimpleDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.CancionUsuarioDTO;
import com.kreitek.PFBKreitekfy.Application.Service.CancionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CancionRestController {

    private final CancionService cancionService;

    public CancionRestController(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    @CrossOrigin
    @GetMapping(value = "/canciones", produces = "application/json")
    ResponseEntity<Page<CancionSimpleDTO>> getAllCanciones(@RequestParam(value = "filter", required = false) String filter, Pageable pageable) {
        Page<CancionSimpleDTO> cancionPage = this.cancionService.getCancionesByCriteriaString(pageable, filter);
        return new ResponseEntity<>(cancionPage, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/canciones/{idCancion}", produces = "application/json")
    ResponseEntity<CancionDTO> getCancion(@PathVariable Long idCancion) {
        return cancionService.getCancionById(idCancion)
                .map(cancionDTO -> new ResponseEntity<>(cancionDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));


    }

    @CrossOrigin
<<<<<<< HEAD
    @PostMapping(value = "/canciones", produces = "application/json", consumes = "application/json")
=======
    @GetMapping(value = "/canciones/{idCancion}/usuarios/{idUsuario}", produces="application/json")
    ResponseEntity<CancionUsuarioDTO> getCancionUsuario(@PathVariable Long idCancion, @PathVariable Long idUsuario) {
        Optional<CancionUsuarioDTO> cancion = this.cancionService.getCancionUsuarioById(idCancion, idUsuario);

        if (cancion.isPresent()) {
            return new ResponseEntity<>(cancion.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/canciones", produces="application/json", consumes="application/json")
>>>>>>> 1cc905e024aa26c25ff3d769fa74ee43b3fd5b6d
    ResponseEntity<CancionDTO> createCancion(@RequestBody CancionDTO cancionDTO) {
        return new ResponseEntity<>(cancionService.saveItem(cancionDTO), HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping(value = "/canciones", produces = "application/json", consumes = "application/json")
    ResponseEntity<CancionDTO> editCancion(@RequestBody CancionDTO cancionDTO) {
        return new ResponseEntity<>(cancionService.saveItem(cancionDTO), HttpStatus.OK);
    }
}
