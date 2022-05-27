package com.kreitek.PFBKreitekfy.Application.Comparer;

import java.util.Comparator;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;

public class ReproduccionesComparer implements Comparator<Cancion> {
    @Override
    public int compare(Cancion cancion1, Cancion cancion2) {
        float reproducciones1 = cancion1.getReproduccion();
        float reproducciones2 = cancion2.getReproduccion();

        return reproducciones1 > reproducciones2 ? -1
            : reproducciones1 < reproducciones2 ? 1
            : 0;
    }

}
