package com.kreitek.PFBKreitekfy.Infrasctructure.Rest;

import com.kreitek.PFBKreitekfy.Application.Dto.AlbumDTO;
import com.kreitek.PFBKreitekfy.Application.Service.AlbumService;

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
public class AlbumRestController {

    private final AlbumService albumService;

    public AlbumRestController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @CrossOrigin
    @GetMapping(value = "/albumes", produces = "application/json")
    ResponseEntity<Page<AlbumDTO>> getAllAlbums(@RequestParam(value = "filter", required = false) String filter, Pageable pageable) {
        Page<AlbumDTO> albumPage = this.albumService.getAlbumsByCriteriaString(pageable, filter);
        return new ResponseEntity<>(albumPage, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/albumes/{idAlbum}", produces = "application/json")
    ResponseEntity<AlbumDTO> getAlbum(@PathVariable Long idAlbum) {
        return albumService.getAlbumById(idAlbum)
                .map(albumDTO -> new ResponseEntity<>(albumDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
