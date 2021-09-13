package com.omniteam.backofisbackend.service;

import com.omniteam.backofisbackend.dto.attibute.AttributeTermDTO;

import java.util.List;

public interface AttributeTermService {

    public List<AttributeTermDTO> getByAttributeTermByAttribute(Integer attributeId) ;

    }
