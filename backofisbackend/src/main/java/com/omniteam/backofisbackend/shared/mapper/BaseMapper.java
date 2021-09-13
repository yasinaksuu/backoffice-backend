package com.omniteam.backofisbackend.shared.mapper;

import java.util.List;

public interface BaseMapper <Entity, DTO>{

    DTO mapToDTO(Entity entity);

    Entity mapToEntity(DTO dto);

    List<DTO> mapToDTOs(List<Entity> entities);

    List<Entity> mapToEntities(List<DTO> dtos);
}
