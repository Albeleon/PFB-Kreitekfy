package com.kreitek.PFBKreitekfy.Application.Dto;

import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import com.kreitek.PFBKreitekfy.Domain.Type.TipoRol;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UsuarioDTO implements Serializable {

    private Long id;
    private String nombre;
    private byte[] imagen;
    private TipoRol rol;


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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public TipoRol getRol() {
        return rol;
    }

    public void setRol(TipoRol rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDTO that = (UsuarioDTO) o;
        return id.equals(that.id) && nombre.equals(that.nombre) && Arrays.equals(imagen, that.imagen);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, nombre, rol);
        result = 31 * result + Arrays.hashCode(imagen);
        return result;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagen=" + Arrays.toString(imagen) +
                ", rol=" + rol +
                '}';
    }
}
