package com.kreitek.PFBKreitekfy.Infrasctructure.Repository;


import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CancionUsuarioRepository extends JpaRepository<CancionUsuario, Long> {

    Optional<CancionUsuario> findByCancion_idAndUsuario_Id(Long cancion_id, Long usuario_id);

}
