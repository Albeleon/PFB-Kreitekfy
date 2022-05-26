package com.kreitek.PFBKreitekfy.Application.Dto;

import java.io.Serializable;
import java.util.Date;

public class CancionDTO implements Serializable {

    private Long id;
    private String nombre;
    private Long duracion;
    private Date fecha;
    private Long artistaId;
    private String artistaNombre;
    private Long albumId;
    private String albumNombre;
    private byte[] albumImagen;
    private Long estiloId;
    private String estiloNombre;


    public CancionDTO() {
    }

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

    public Long getDuracion() {
        return duracion;
    }

    public void setDuracion(Long duracion) {
        this.duracion = duracion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public byte[] getAlbumImagen() {
        return albumImagen;
    }

    public void setAlbumImagen(byte[] albumImagen) {
        this.albumImagen = albumImagen;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumNombre() {
        return albumNombre;
    }

    public void setAlbumNombre(String albumNombre) {
        this.albumNombre = albumNombre;
    }


    public Long getEstiloId() {
        return estiloId;
    }

    public void setEstiloId(Long estiloId) {
        this.estiloId = estiloId;
    }

    public String getEstiloNombre() {
        return estiloNombre;
    }

    public void setEstiloNombre(String estiloNombre) {
        this.estiloNombre = estiloNombre;
    }


}
