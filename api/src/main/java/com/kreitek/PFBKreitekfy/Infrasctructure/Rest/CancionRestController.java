package com.kreitek.PFBKreitekfy.Infrasctructure.Rest;


import java.util.Optional;

import com.kreitek.PFBKreitekfy.Application.Dto.CancionDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.CancionSimpleDTO;
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
    @GetMapping(value = "/canciones", produces="application/json")
    ResponseEntity<Page<CancionSimpleDTO>> getAllCanciones(@RequestParam(value = "filter", required = false) String filter, Pageable pageable) {
        Page<CancionSimpleDTO> cancionPage = this.cancionService.getCancionesByCriteriaString(pageable, filter);
        return new ResponseEntity<>(cancionPage, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/canciones/{idCancion}", produces="application/json")
    ResponseEntity<CancionDTO> getCancion(@PathVariable Long idCancion) {
        Optional<CancionDTO> cancion = this.cancionService.getCancionById(idCancion);

        if (cancion.isPresent()) {
            return new ResponseEntity<>(cancion.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/canciones", produces="application/json", consumes="application/json")
    ResponseEntity<CancionDTO> createCancion(@RequestBody CancionDTO cancionDTO) {

        CancionDTO cancionSaved = this.cancionService.saveItem(cancionDTO);
        return new ResponseEntity<>(cancionSaved, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping(value = "/canciones", produces="application/json", consumes="application/json")
    ResponseEntity<CancionDTO> editCancion(@RequestBody CancionDTO cancionDTO) {

        CancionDTO cancionUpdated = this.cancionService.saveItem(cancionDTO);
        return new ResponseEntity<>(cancionUpdated, HttpStatus.OK);
    }
}
