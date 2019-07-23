package com.example.entrega.component;

import com.example.entrega.dto.RateDto;
import com.example.entrega.model.RateEntity;

import java.util.List;

public interface MapperRateService {
    RateEntity map(RateDto rateDto);
    RateDto map(RateEntity rateEntity);
    List<RateDto> map(List<RateEntity> rateEntities);
    List<RateEntity> map2(List<RateDto> rateDtos);
}
