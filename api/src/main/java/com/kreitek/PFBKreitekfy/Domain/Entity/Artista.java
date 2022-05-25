package com.kreitek.PFBKreitekfy.Domain.Entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artista_sequence")
    @SequenceGenerator(name = "artista_sequence")
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL)
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

    public Set<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(Set<Cancion> canciones) {
        this.canciones = canciones;
    }
}
