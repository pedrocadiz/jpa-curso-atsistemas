package com.example.entrega.component;

import com.example.entrega.dto.CarDto;
import com.example.entrega.model.CarEntity;

import java.util.List;

public interface MapperCarService {
    CarEntity map(CarDto carDto);
    CarDto map(CarEntity carEntity);
    List<CarDto> map(List<CarEntity> carEntities);
    List<CarEntity> map2(List<CarDto> carDtos);
}
