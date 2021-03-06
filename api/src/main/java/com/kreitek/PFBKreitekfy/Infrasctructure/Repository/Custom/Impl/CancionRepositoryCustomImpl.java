package com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Custom.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Entity.Estilo;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Custom.CancionRepositoryCustom;

import org.springframework.stereotype.Repository;

@Repository
public class CancionRepositoryCustomImpl implements CancionRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cancion> find5CancionesRecomendadas(Long usuarioId) {
        Query query = entityManager.createQuery("SELECT e FROM Estilo AS e INNER JOIN Cancion AS c ON e.id = c.estilo.id INNER JOIN CancionUsuario AS cu ON c.id = cu.id.cancionId WHERE cu.id.usuarioId = " + usuarioId + " GROUP BY e.id ORDER BY sum(cu.reproduccion) DESC");
        List<Estilo> estilos = query.setMaxResults(2).getResultList();
        
        String whereEstilo = "";
        for (int index = 0; index < estilos.size(); index++) {
            whereEstilo += (index > 0 ? " OR " : "") + "c.estilo.id = " + estilos.get(index).getId();
        }

        List<Cancion> canciones = entityManager.createQuery("SELECT c FROM Cancion AS c WHERE c.valoracionMedia >= 3 " + (whereEstilo != "" ? ("AND (" + whereEstilo + ")") : "" ) + " ORDER BY c.reproduccion DESC").setMaxResults(5).getResultList();

        return canciones;

    }
    
}
