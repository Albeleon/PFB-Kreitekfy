package com.kreitek.PFBKreitekfy.Infrasctructure.Rest;

import com.kreitek.PFBKreitekfy.Application.Dto.ArtistaDTO;
import com.kreitek.PFBKreitekfy.Application.Service.ArtistaService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistaRestController {

    private final ArtistaService artistaService;

    public ArtistaRestController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @CrossOrigin
    @GetMapping(value = "/artistas", produces = "application/json")
    ResponseEntity<Page<ArtistaDTO>> getAllArtistas(@RequestParam(value = "filter", required = false) String filter, Pageable pageable) {
        Page<ArtistaDTO> artistaPage = this.artistaService.getArtistasByCriteriaString(pageable, filter);
        return new ResponseEntity<>(artistaPage, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/artistas/{idArtista}", produces = "application/json")
    ResponseEntity<ArtistaDTO> getArtista(@PathVariable Long idArtista) {

        return artistaService.getArtistaById(idArtista)
                .map(artistaDTO -> new ResponseEntity<>(artistaDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
