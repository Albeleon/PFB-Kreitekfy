package com.kreitek.PFBKreitekfy.Application.Dto;

import java.io.Serializable;
import java.util.Objects;

public class CancionUsuarioDTO implements Serializable {

    private Long cancionId;
    private Long usuarioId;
    private Long reproduccion;
    private Long valoracion;


    public Long getCancionId() {
        return cancionId;
    }

    public void setCancionId(Long cancionId) {
        this.cancionId = cancionId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getReproducciones() {
        return reproduccion;
    }

    public void setReproducciones(Long reproduccion) {
        this.reproduccion = reproduccion;
    }

    public Long getValoracion() {
        return valoracion;
    }

    public void setValoracion(Long valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CancionUsuarioDTO that = (CancionUsuarioDTO) o;
        return cancionId.equals(that.cancionId) && usuarioId.equals(that.usuarioId) && reproduccion.equals(that.reproduccion) && valoracion.equals(that.valoracion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cancionId, usuarioId, reproduccion, valoracion);
    }

    @Override
    public String toString() {
        return "CancionUsuarioDTO{" +
                "cancionId=" + cancionId +
                ", usuarioId=" + usuarioId +
                ", reproduccion=" + reproduccion +
                ", valoracion=" + valoracion +
                '}';
    }
}
