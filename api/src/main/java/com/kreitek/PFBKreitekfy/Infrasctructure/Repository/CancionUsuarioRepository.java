package com.kreitek.PFBKreitekfy.Infrasctructure.Repository;

import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionUsuarioRepository extends JpaRepository<CancionUsuario, Long> {

    CancionUsuario findByCancion_idAndUsuario_Id(Long cancion_id, Long usuario_id);

}
