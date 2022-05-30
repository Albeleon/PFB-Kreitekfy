package com.kreitek.PFBKreitekfy.Infrasctructure.Rest;

import com.kreitek.PFBKreitekfy.Application.Dto.EstiloDTO;
import com.kreitek.PFBKreitekfy.Application.Service.EstiloService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EstiloRestController {

    private final EstiloService estiloService;

    public EstiloRestController(EstiloService estiloService) {
        this.estiloService = estiloService;
    }

    @CrossOrigin
    @GetMapping(value = "/estilos", produces = "application/json")
    ResponseEntity<Page<EstiloDTO>> getAllEstilos(@RequestParam(value = "filter", required = false) String filter, Pageable pageable) {
        Page<EstiloDTO> estiloPage = this.estiloService.getEstilosByCriteriaString(pageable, filter);
        return new ResponseEntity<>(estiloPage, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/estilos/{idEstilo}", produces = "application/json")
    ResponseEntity<EstiloDTO> getEstilo(@PathVariable Long idEstilo) {
        return estiloService.getEstiloById(idEstilo)
                .map(estiloDTO -> new ResponseEntity<>(estiloDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));


    }

    @CrossOrigin
    @PostMapping(value = "/estilos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<EstiloDTO> insertEstilo(@RequestBody EstiloDTO estiloDTO) {
        return new ResponseEntity<>(this.estiloService.saveEstilo(estiloDTO) , HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping(value = "/estilos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<EstiloDTO> editEstilo(@RequestBody EstiloDTO estiloDTO){
        return new ResponseEntity<>(this.estiloService.saveEstilo(estiloDTO) , HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/estilos/{estiloId}", produces = "application/json")
    public ResponseEntity<Void> deleteEstiloById(@PathVariable Long estiloId) {
        this.estiloService.deleteEstiloById(estiloId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
