package com.kreitek.PFBKreitekfy.Infrasctructure.Repository;

import com.kreitek.PFBKreitekfy.Domain.Entity.Estilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EstiloRepository extends JpaRepository<Estilo, Long>, JpaSpecificationExecutor<Estilo> {

}
