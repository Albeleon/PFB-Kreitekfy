package com.kreitek.PFBKreitekfy.Infrasctructure.Repository;

import java.util.Optional;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CancionUsuarioRepository extends JpaRepository<CancionUsuario, Long>, JpaSpecificationExecutor<CancionUsuario> {
    Optional<CancionUsuario> findByCancion_idAndUsuario_Id(Long cancion_id, Long usuario_id);

    @Query("SELECT count(cu.id.cancionId) AS count FROM CancionUsuario cu WHERE cu.id.cancionId = :idCancion AND cu.valoracion IS NOT null")
    Long findNumeroUsuariosValoracion(@Param("idCancion") Long idCancion);
}
