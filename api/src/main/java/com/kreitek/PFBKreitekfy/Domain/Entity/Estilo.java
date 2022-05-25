package com.kreitek.PFBKreitekfy.Domain.Entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "estilos")
public class Estilo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estilo_sequence")
    @SequenceGenerator(name = "estilo_sequence")
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "estilo", cascade = CascadeType.ALL)
    Set<Cancion> canciones;

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
