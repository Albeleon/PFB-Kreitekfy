package com.kreitek.PFBKreitekfy.Infrasctructure.Repository;

<<<<<<< HEAD
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionUsuarioRepository extends JpaRepository<CancionUsuario, Long> {

    CancionUsuario findByCancion_idAndUsuario_Id(Long cancion_id, Long usuario_id);

}
=======
import java.util.Optional;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Domain.Entity.CancionUsuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CancionUsuarioRepository extends JpaRepository<CancionUsuario, Long>, JpaSpecificationExecutor<Cancion> {
    Optional<CancionUsuario> findByCancion_idAndUsuario_Id(Long cancion_id, Long usuario_id);
}
>>>>>>> 1cc905e024aa26c25ff3d769fa74ee43b3fd5b6d
