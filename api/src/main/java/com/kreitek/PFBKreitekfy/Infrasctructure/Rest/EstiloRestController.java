package com.kreitek.PFBKreitekfy.Infrasctructure.Rest;


import com.kreitek.PFBKreitekfy.Application.Dto.EstiloDTO;
import com.kreitek.PFBKreitekfy.Application.Service.EstiloService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstiloRestController {

    private final EstiloService estiloService;

    public EstiloRestController(EstiloService estiloService) {
        this.estiloService = estiloService;
    }

    @CrossOrigin
    @GetMapping(value = "/estilos", produces="application/json")
    ResponseEntity<Page<EstiloDTO>> getAllEstilos(@RequestParam(value = "filter", required = false) String filter, Pageable pageable) {
        Page<EstiloDTO> estiloPage = this.estiloService.getEstilosByCriteriaString(pageable, filter);
        return new ResponseEntity<>(estiloPage, HttpStatus.OK);
    }

}