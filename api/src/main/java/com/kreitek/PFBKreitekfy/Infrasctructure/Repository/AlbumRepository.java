package com.kreitek.PFBKreitekfy.Infrasctructure.Repository;

import com.kreitek.PFBKreitekfy.Domain.Entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AlbumRepository extends JpaRepository<Album, Long>, JpaSpecificationExecutor<Album> {

}
