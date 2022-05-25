package com.kreitek.PFBKreitekfy.Domain.Entity;


import com.kreitek.PFBKreitekfy.Domain.Type.TipoRol;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence")
    @SequenceGenerator(name = "usuario_sequence")
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Lob
    private byte[] imagen;

    @Column(nullable = false)
    private TipoRol rol;

    @OneToMany(mappedBy = "usuario")
    private Set<CancionUsuario> usuariosCanciones;

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

    public Set<CancionUsuario> getUsuariosCanciones() {
        return usuariosCanciones;
    }

    public void setUsuariosCanciones(Set<CancionUsuario> usuariosCanciones) {
        this.usuariosCanciones = usuariosCanciones;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass() || id == null) return false;
        Usuario usuario = (Usuario) obj;
        return id.equals(usuario.id);
    }
}
