package com.kreitek.PFBKreitekfy.Domain.Entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "albumes")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_sequence")
    @SequenceGenerator(name = "album_sequence")
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Lob
    private byte[] imagen;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private Set<Cancion> canciones;

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

    public Set<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(Set<Cancion> canciones) {
        this.canciones = canciones;
    }
}
