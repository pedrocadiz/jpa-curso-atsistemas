package com.example.entrega.component;

import com.example.entrega.dto.RentalDto;
import com.example.entrega.model.RentalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperRentalServiceImpl implements MapperRentalService{

    @Autowired MapperCarService mapperCarService;
    @Autowired MapperClientService mapperClientService;

    @Override
    public RentalEntity map(RentalDto rentalDto) {
        final RentalEntity rentalEntity = new RentalEntity();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-M-[uuuu][uu]");


        rentalEntity.setId(rentalDto.getId());
        rentalEntity.setCarEntity(mapperCarService.map(rentalDto.getCar()));
        rentalEntity.setClientEntity(mapperClientService.map(rentalDto.getClient()));
        rentalEntity.setPrice(rentalDto.getPrice());
        rentalEntity.setStartDate(LocalDate.parse(rentalDto.getStartDate(),format));
        rentalEntity.setEndDate(LocalDate.parse(rentalDto.getEndDate(),format));

        return rentalEntity;
    }

    @Override
    public RentalDto map(RentalEntity rentalEntity) {
        final RentalDto rentalDto = new RentalDto();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-M-[uuuu]");

        rentalDto.setId(rentalEntity.getId());
        rentalDto.setCar(mapperCarService.map(rentalEntity.getCarEntity()));
        rentalDto.setClient(mapperClientService.map(rentalEntity.getClientEntity()));
        rentalDto.setPrice(rentalEntity.getPrice());
        rentalDto.setStartDate((rentalEntity.getStartDate()).format(format));
        rentalDto.setEndDate((rentalEntity.getEndDate()).format(format));

        return rentalDto;
    }

    @Override
    public List<RentalDto> map(List<RentalEntity> rentalEntities) {
        List<RentalDto> rentalsDto = new ArrayList<>();
        for (RentalEntity b : rentalEntities) {
            RentalDto rentalDto = map(b);
            rentalsDto.add(rentalDto);
        }
        return rentalsDto;
    }

    @Override
    public List<RentalEntity> map2(List<RentalDto> rentalDtos) {
        List<RentalEntity> rentalEntities = new ArrayList<>();
        for (RentalDto b : rentalDtos) {
            RentalEntity rentalEntity = map(b);
            rentalEntities.add(rentalEntity);
        }
        return rentalEntities;
    }
}
