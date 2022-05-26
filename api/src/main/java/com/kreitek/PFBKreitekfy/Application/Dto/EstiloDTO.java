package com.kreitek.PFBKreitekfy.Application.Dto;

import java.io.Serializable;
import java.util.Objects;

public class EstiloDTO implements Serializable {

    private Long id;
    private String nombre;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstiloDTO estiloDTO = (EstiloDTO) o;
        return id.equals(estiloDTO.id) && nombre.equals(estiloDTO.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        return "EstiloDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
