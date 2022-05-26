package com.kreitek.PFBKreitekfy.Infrasctructure.Specs;

import com.kreitek.PFBKreitekfy.Domain.Entity.Album;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.Shared.EntitySpecification;
import com.kreitek.PFBKreitekfy.Infrasctructure.Specs.Shared.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class AlbumSpecification extends EntitySpecification<Album> implements Specification<Album> {

    public AlbumSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }
}