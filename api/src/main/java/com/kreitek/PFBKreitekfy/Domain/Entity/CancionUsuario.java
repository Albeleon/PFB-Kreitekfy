package com.kreitek.PFBKreitekfy.Domain.Entity;


import com.kreitek.PFBKreitekfy.Domain.Key.CancionUsuarioKey;

import javax.persistence.*;

@Entity
@Table(name = "canciones_usuarios")
public class CancionUsuario {

    @EmbeddedId
    private CancionUsuarioKey id;

    @ManyToOne
    @MapsId("cancionId")
    @JoinColumn(name = "cancion_id")
    private Cancion cancion;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(nullable = false)
    private Long reproducciones;

    private Long valoracion;

    public CancionUsuarioKey getId() {
        return id;
    }

    public void setId(CancionUsuarioKey id) {
        this.id = id;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(Long reproducciones) {
        this.reproducciones = reproducciones;
    }

    public Long getValoracion() {
        return valoracion;
    }

    public void setValoracion(Long valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass() || id == null) return false;
        CancionUsuario cancionUsuario = (CancionUsuario) obj;
        return id.equals(cancionUsuario.id);
    }
}
