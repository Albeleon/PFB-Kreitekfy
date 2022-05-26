package com.kreitek.PFBKreitekfy.Infrasctructure.Specs;

import com.kreitek.PFBKreitekfy.Domain.Entity.Estilo;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.Shared.EntitySpecification;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.Shared.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class EstiloSpecification extends EntitySpecification<Estilo> implements Specification<Estilo> {

    public EstiloSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }
}