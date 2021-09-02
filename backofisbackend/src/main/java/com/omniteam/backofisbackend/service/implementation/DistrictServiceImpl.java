package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.district.DistrictDto;
import com.omniteam.backofisbackend.entity.District;
import com.omniteam.backofisbackend.repository.DistrictRepository;
import com.omniteam.backofisbackend.service.DistrictService;
import com.omniteam.backofisbackend.shared.mapper.DistrictMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    public DistrictServiceImpl(DistrictRepository districtRepository, DistrictMapper districtMapper) {
        this.districtRepository = districtRepository;
        this.districtMapper = districtMapper;
    }

    @Override
    public DataResult<List<DistrictDto>> getDistrictsByCity(int cityId) {
        List<District> districtList = this.districtRepository.getDistrictsByCity(cityId);
        List<DistrictDto> districtDtoList = this.districtMapper.toDistrictDtoList(districtList);
        return new SuccessDataResult<>(districtDtoList);
    }
}
