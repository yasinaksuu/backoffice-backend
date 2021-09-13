package com.omniteam.backofisbackend.controller;


import com.omniteam.backofisbackend.base.ResponsePayload;
import com.omniteam.backofisbackend.entity.AttributeTerm;
import com.omniteam.backofisbackend.service.AttributeService;
import com.omniteam.backofisbackend.service.AttributeTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/attributes")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private AttributeTermService attributeTermService;

    @GetMapping(path = "/getbyid/{attributeId}")
    public ResponsePayload getByAttributeTermByAttribute(@PathVariable(name = "attributeId") int attributeId) {
        return new ResponsePayload(HttpStatus.OK.value(),attributeTermService.getByAttributeTermByAttribute(attributeId));
    }

}
