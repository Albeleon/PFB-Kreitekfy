package com.kreitek.PFBKreitekfy.Application.Dto;

import java.io.Serializable;
import java.util.Date;

public class CancionSimpleDTO implements Serializable {
    private Long id;
    private String nombre;
    private Long artistaId;
    private String artistaNombre;
    private Long albumId;
    private byte[] albumImagen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(Long artistaId) {
        this.artistaId = artistaId;
    }

    public String getArtistaNombre() {
        return artistaNombre;
    }

    public void setArtistaNombre(String artistaNombre) {
        this.artistaNombre = artistaNombre;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public byte[] getAlbumImagen() {
        return albumImagen;
    }

    public void setAlbumImagen(byte[] albumImagen) {
        this.albumImagen = albumImagen;
    }
}
