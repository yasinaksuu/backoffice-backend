package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.country.CountryDto;
import com.omniteam.backofisbackend.entity.Country;
import com.omniteam.backofisbackend.repository.CountryRepository;
import com.omniteam.backofisbackend.service.CountryService;
import com.omniteam.backofisbackend.shared.mapper.CountryMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;
    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    public DataResult<List<CountryDto>> getAll(String countryName) {
        List<Country> countryList = this.countryRepository.findFirst10ByCountryNameContainingIgnoreCaseOrderByCountryIdAsc(countryName);
        List<CountryDto> countryDtoList = this.countryMapper.toCountryDtoList(countryList);
        return new SuccessDataResult<>("",countryDtoList);
    }
}
