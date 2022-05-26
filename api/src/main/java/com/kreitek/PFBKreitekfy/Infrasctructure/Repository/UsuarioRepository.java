package com.kreitek.PFBKreitekfy.Infrasctructure.Repository;

import com.kreitek.PFBKreitekfy.Domain.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
