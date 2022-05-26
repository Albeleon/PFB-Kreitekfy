package com.kreitek.PFBKreitekfy.Infrasctructure.Specs;

import com.kreitek.PFBKreitekfy.Domain.Entity.Artista;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.Shared.EntitySpecification;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.Shared.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ArtistaSpecification extends EntitySpecification<Artista> implements Specification<Artista> {

    public ArtistaSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }
}