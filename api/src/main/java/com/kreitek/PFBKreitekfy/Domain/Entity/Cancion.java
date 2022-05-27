package com.kreitek.PFBKreitekfy.Domain.Entity;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "canciones")
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cancion_sequence")
    @SequenceGenerator(name = "cancion_sequence")
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Long duracion;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private Long reproduccion = 0L;

    @ManyToOne
    @JoinColumn(name = "artista_id", nullable = false)
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "estilo_id", nullable = false)
    private Estilo estilo;

    @OneToMany(mappedBy = "cancion")
    private Set<CancionUsuario> cancionesUsuarios;

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

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public Set<CancionUsuario> getCancionesUsuarios() {
        return cancionesUsuarios;
    }

    public void setCancionesUsuarios(Set<CancionUsuario> cancionesUsuarios) {
        this.cancionesUsuarios = cancionesUsuarios;
    }

    public Long getReproduccion() {
        return reproduccion;
    }

    public void setReproduccion(Long reproduccion) {
        this.reproduccion = reproduccion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass() || id == null) return false;
        Cancion cancion = (Cancion) obj;
        return id.equals(cancion.id);
    }
}
