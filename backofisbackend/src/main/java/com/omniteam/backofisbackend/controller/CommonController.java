package com.omniteam.backofisbackend.controller;

import com.omniteam.backofisbackend.dto.country.CountryDto;
import com.omniteam.backofisbackend.service.CountryService;
import com.omniteam.backofisbackend.shared.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/common")
public class CommonController {

    private final CountryService countryService;
    @Autowired
    public CommonController(CountryService countryService){
        this.countryService = countryService;
    }
    @GetMapping(path = "/getcountries")
    public ResponseEntity<DataResult<List<CountryDto>>> getCountries(){
        return ResponseEntity.ok(this.countryService.getAll());
    }
}
