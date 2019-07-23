package com.example.entrega.component;

import com.example.entrega.dto.CarDto;
import com.example.entrega.model.CarEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperCarServiceImpl implements MapperCarService {

    @Override
    public CarEntity map(CarDto carDto) {

        final CarEntity carEntity = new CarEntity();

        carEntity.setCarPlate(carDto.getCarPlate());
        carEntity.setId(carDto.getId());
        carEntity.setRegistrationYear(carDto.getRegistrationYear());

        return carEntity;
    }

    @Override
    public CarDto map(CarEntity carEntity) {

        final CarDto carDto = new CarDto();

        carDto.setCarPlate(carEntity.getCarPlate());
        carDto.setId(carEntity.getId());
        carDto.setRegistrationYear(carEntity.getRegistrationYear());

        return carDto;
    }

    @Override
    public List<CarDto> map(List<CarEntity> carEntities){
        List<CarDto> carsDto = new ArrayList<>();
        for (CarEntity b : carEntities) {
            CarDto carDto = map(b);
            carsDto.add(carDto);
        }
        return carsDto;
    }

    @Override
    public List<CarEntity> map2(List<CarDto> carDtos){
        List<CarEntity> carEntities = new ArrayList<>();
        for (CarDto b : carDtos) {
            CarEntity carEntity = map(b);
            carEntities.add(carEntity);
        }
        return carEntities;
    }
}
