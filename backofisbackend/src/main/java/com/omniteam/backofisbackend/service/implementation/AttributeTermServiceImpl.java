package com.omniteam.backofisbackend.service.implementation;


import com.omniteam.backofisbackend.dto.attibute.AttributeDTO;
import com.omniteam.backofisbackend.dto.attibute.AttributeTermDTO;
import com.omniteam.backofisbackend.entity.Attribute;
import com.omniteam.backofisbackend.entity.AttributeTerm;
import com.omniteam.backofisbackend.repository.AttributeTermRepository;
import com.omniteam.backofisbackend.service.AttributeTermService;
import com.omniteam.backofisbackend.shared.mapper.AttributeTermMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeTermServiceImpl implements AttributeTermService {

    private final AttributeTermRepository attributeTermRepository;
    private final AttributeTermMapper attributeTermMapper;
    @Autowired
    public AttributeTermServiceImpl(AttributeTermRepository attributeTermRepository, AttributeTermMapper attributeTermMapper) {
        this.attributeTermRepository = attributeTermRepository;
        this.attributeTermMapper = attributeTermMapper;
    }
    public DataResult<List<AttributeTermDTO>> getByAttributeTermByAttribute(Integer attributeId) {
        List<AttributeTerm> attributeTerm = attributeTermRepository.findAllByAttribute_AttributeId(attributeId);
        List<AttributeTermDTO> attributeTermDTOS = attributeTermMapper.mapToDTOs(attributeTerm);
        return new SuccessDataResult<>(attributeTermDTOS);
    }
}
