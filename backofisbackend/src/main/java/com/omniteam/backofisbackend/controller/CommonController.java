package com.omniteam.backofisbackend.controller;

import com.omniteam.backofisbackend.dto.city.CityDto;
import com.omniteam.backofisbackend.dto.country.CountryDto;
import com.omniteam.backofisbackend.service.CityService;
import com.omniteam.backofisbackend.service.CountryService;
import com.omniteam.backofisbackend.shared.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/common")
public class CommonController {

    private final CountryService countryService;
    private final CityService cityService;
    @Autowired
    public CommonController(CountryService countryService, CityService cityService){
        this.countryService = countryService;
        this.cityService = cityService;
    }
    @GetMapping(path = "/getcountries")
    public ResponseEntity<DataResult<List<CountryDto>>> getCountries(){
        return ResponseEntity.ok(this.countryService.getAll());
    }

    @GetMapping(path = "/{countryid}/getcitiesbycountry")
    public ResponseEntity<DataResult<List<CityDto>>> getCitiesByCountry(@PathVariable(name = "countryid") int countryId){
        return ResponseEntity.ok(this.cityService.getCitiesByCountry(countryId));
    }
}
