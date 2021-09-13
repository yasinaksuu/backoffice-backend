package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.attibute.AttributeDTO;
import com.omniteam.backofisbackend.dto.category.CategoryDTO;
import com.omniteam.backofisbackend.entity.Attribute;
import com.omniteam.backofisbackend.entity.Category;
import com.omniteam.backofisbackend.repository.AttributeRepository;
import com.omniteam.backofisbackend.service.AttributeService;
import com.omniteam.backofisbackend.shared.mapper.AttributeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeServiceImpl  implements AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private AttributeMapper attributeMapper;




}
