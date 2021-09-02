package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.city.CityDto;
import com.omniteam.backofisbackend.entity.City;
import com.omniteam.backofisbackend.repository.CityRepository;
import com.omniteam.backofisbackend.service.CityService;
import com.omniteam.backofisbackend.shared.mapper.CityMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @Override
    public DataResult<List<CityDto>> getCitiesByCountry(int countryId) {
        List<City> cityList = this.cityRepository.getCitiesByCountryId(countryId);
        List<CityDto> cityDtoList = this.cityMapper.toCityDtoList(cityList);
        return new SuccessDataResult<>(cityDtoList);
    }
}