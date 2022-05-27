package com.kreitek.PFBKreitekfy.Application.Comparer;

import java.util.Comparator;
import java.util.Iterator;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import com.kreitek.PFBKreitekfy.Domain.Entity.Estilo;

public class ReproduccionesUsuarioComparer implements Comparator<Estilo> {
    private Long usuarioId;

    public ReproduccionesUsuarioComparer(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int compare(Estilo estilo1, Estilo estilo2) {
        float reproducciones1 = this.getReproduccionesPorUsuario(estilo1);
        float reproducciones2 = this.getReproduccionesPorUsuario(estilo2);

        return reproducciones1 > reproducciones2 ? -1
            : reproducciones1 < reproducciones2 ? 1
            : 0;
    }

    private Long getReproduccionesPorUsuario(Estilo estilo) {
        Iterator<Cancion> canciones = estilo.getCanciones().iterator();
        Long reproducciones = 0L;

        while (canciones.hasNext()) {
            reproducciones += getReproduccionesPorUsuario(canciones.next());
        }
        
        return reproducciones;
    }

    private Long getReproduccionesPorUsuario(Cancion cancion) {
        Iterator<CancionUsuario> cancionesUsuario = cancion.getCancionesUsuarios().iterator();

        while (cancionesUsuario.hasNext()) {
            CancionUsuario cancionUsuario = cancionesUsuario.next();
            if (cancionUsuario.getUsuario().getId().equals(usuarioId)) {
                return cancionUsuario.getReproduccion();
            }
        }
        
        return 0L;
    }
}
