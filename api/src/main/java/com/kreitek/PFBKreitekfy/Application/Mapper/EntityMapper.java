package com.kreitek.PFBKreitekfy.Application.Mapper;

import java.util.List;

public interface EntityMapper<Dto, Entity> {

    Entity toEntity(Dto dto);

    Dto toDto(Entity entity);

    List<Entity> toList(List<Dto> listDto);

    List<Dto> toListDto(List<Entity> listEntity);
}
