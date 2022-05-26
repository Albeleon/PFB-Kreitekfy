package com.kreitek.PFBKreitekfy.Infrasctructure.Rest;


import com.kreitek.PFBKreitekfy.Application.Dto.CancionDTO;
import com.kreitek.PFBKreitekfy.Application.Dto.CancionSimpleDTO;
import com.kreitek.PFBKreitekfy.Application.Service.CancionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping(value = "/canciones", produces="application/json", consumes="application/json")
    ResponseEntity<CancionDTO> createCancion(@RequestBody CancionDTO cancionDTO) {

        CancionDTO cancionSaved = this.cancionService.saveItem(cancionDTO);
        System.out.println("CancioDTO saved: returning " + cancionSaved.getNombre());
        return new ResponseEntity<>(cancionSaved, HttpStatus.CREATED);
    }

}
