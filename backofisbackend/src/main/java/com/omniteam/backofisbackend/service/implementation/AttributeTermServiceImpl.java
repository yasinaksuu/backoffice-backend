package com.omniteam.backofisbackend.service.implementation;


import com.omniteam.backofisbackend.dto.attibute.AttributeDTO;
import com.omniteam.backofisbackend.dto.attibute.AttributeTermDTO;
import com.omniteam.backofisbackend.entity.Attribute;
import com.omniteam.backofisbackend.entity.AttributeTerm;
import com.omniteam.backofisbackend.repository.AttributeTermRepository;
import com.omniteam.backofisbackend.service.AttributeTermService;
import com.omniteam.backofisbackend.shared.mapper.AttributeTermMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeTermServiceImpl implements AttributeTermService {

    @Autowired
    private AttributeTermRepository attributeTermRepository;

    @Autowired
    private AttributeTermMapper attributeTermMapper;

    public List<AttributeTermDTO> getByAttributeTermByAttribute(Integer attributeId) {
        List<AttributeTerm> attributeTerm = attributeTermRepository.findAllByAttribute_AttributeId(attributeId);
        List<AttributeTermDTO> attributeTermDTOS =attributeTermMapper.mapToDTOs(attributeTerm);
         return attributeTermDTOS;
    }

}
