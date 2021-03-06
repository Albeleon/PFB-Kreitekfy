package com.kreitek.PFBKreitekfy.Infrasctructure.Repository;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Infrasctructure.Repository.Custom.CancionRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CancionRepository extends JpaRepository<Cancion, Long>, JpaSpecificationExecutor<Cancion>, CancionRepositoryCustom {
}
