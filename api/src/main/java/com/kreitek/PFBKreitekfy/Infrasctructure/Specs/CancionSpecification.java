package com.kreitek.PFBKreitekfy.Infrasctructure.Specs;

import com.kreitek.PFBKreitekfy.Domain.Entity.Cancion;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.Shared.EntitySpecification;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.Shared.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CancionSpecification extends EntitySpecification<Cancion> implements Specification<Cancion> {

    public CancionSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }
}
