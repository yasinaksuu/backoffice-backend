package com.omniteam.backofisbackend.shared.mapper;


import com.omniteam.backofisbackend.dto.attibute.AttributeDTO;
import com.omniteam.backofisbackend.entity.Attribute;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {AttributeTermMapper.class }
)
public interface AttributeMapper {

    AttributeDTO mapToDTO(Attribute attribute);
}
