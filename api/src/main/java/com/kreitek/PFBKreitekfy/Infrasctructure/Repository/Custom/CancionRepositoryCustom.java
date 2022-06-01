package com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Custom;

import java.util.List;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;

public interface CancionRepositoryCustom {
    List<Cancion> find5CancionesRecomendadas(Long usuarioId);
}
