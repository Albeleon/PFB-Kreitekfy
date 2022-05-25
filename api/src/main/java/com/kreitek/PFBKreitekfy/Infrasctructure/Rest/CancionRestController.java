package com.kreitek.PFBKreitekfy.Infrasctructure.Rest;


import com.kreitek.PFBKreitekfy.Application.Service.CancionService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CancionRestController {

    private final CancionService cancionService;


    public CancionRestController(CancionService cancionService) {
        this.cancionService = cancionService;
    }
}
