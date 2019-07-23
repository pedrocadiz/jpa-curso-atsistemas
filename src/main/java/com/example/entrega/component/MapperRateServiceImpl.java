package com.example.entrega.component;

import com.example.entrega.dto.RateDto;
import com.example.entrega.model.RateEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperRateServiceImpl implements MapperRateService {

    @Override
    public RateEntity map(RateDto rateDto) {
        final RateEntity rateEntity = new RateEntity();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-M-[uuuu][uu]");

        rateEntity.setId(rateDto.getId());
        rateEntity.setPrice(rateDto.getPrice());
        rateEntity.setStartDate(LocalDate.parse(rateDto.getStartDate(), format));
        rateEntity.setEndDate(LocalDate.parse(rateDto.getEndDate(),format));

        return rateEntity;
    }

    @Override
    public RateDto map(RateEntity rateEntity) {
        final RateDto rateDto = new RateDto();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-M-[uuuu]");

        rateDto.setId(rateEntity.getId());
        rateDto.setPrice(rateEntity.getPrice());
        rateDto.setStartDate((rateEntity.getStartDate()).format(format));
        rateDto.setEndDate((rateEntity.getEndDate()).format(format));

        return rateDto;
    }

    @Override
    public List<RateDto> map(List<RateEntity> rateEntities) {
        List<RateDto> ratesDto = new ArrayList<>();
        for (RateEntity b : rateEntities) {
            RateDto carDto = map(b);
            ratesDto.add(carDto);
        }
        return ratesDto;
    }

    @Override
    public List<RateEntity> map2(List<RateDto> rateDtos) {
        List<RateEntity> rateEntities = new ArrayList<>();
        for (RateDto b : rateDtos) {
            RateEntity rateEntity = map(b);
            rateEntities.add(rateEntity);
        }
        return rateEntities;
    }


}
