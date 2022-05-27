package com.kreitek.PFBKreitekfy.Application.Comparer;

import java.util.Comparator;
import java.util.Iterator;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;

public class ValoracionComparer implements Comparator<Cancion> {
    @Override
    public int compare(Cancion cancion1, Cancion cancion2) {
        float valoracion1 = this.getValoracionMedia(cancion1);
        float valoracion2 = this.getValoracionMedia(cancion2);

        return valoracion1 > valoracion2 ? -1
            : valoracion1 < valoracion2 ? 1
            : 0;
    }

    private float getValoracionMedia(Cancion cancion) {
        Iterator<CancionUsuario> cancionesUsuario = cancion.getCancionesUsuarios().iterator();
        Float valoracion = 0f;
        int numeroUsuarios = 0;

        while (cancionesUsuario.hasNext()) {
            CancionUsuario cancionUsuario = cancionesUsuario.next();
            if (cancionUsuario.getValoracion() != null) {
                valoracion += cancionUsuario.getValoracion();
                numeroUsuarios++;
            }
        }
        
        return numeroUsuarios > 0 ? valoracion / numeroUsuarios : 0;
    }
  }