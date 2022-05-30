package com.kreitek.PFBKreitekfy.Infrasctructure.Repository;

import java.util.List;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Custom.CancionRepositoryCustom;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface CancionRepository extends JpaRepository<Cancion, Long>, JpaSpecificationExecutor<Cancion>, CancionRepositoryCustom {
    
    @Query("SELECT c FROM Cancion AS c INNER JOIN CancionUsuario AS cu ON c.id = cu.id.cancionId GROUP BY c.id ORDER BY avg(cu.valoracion) DESC")
    List<Cancion> find5CancionesMasValoradas(Pageable pageable);

}
