package com.example.entrega.component;

import com.example.entrega.dto.RentalDto;
import com.example.entrega.model.RentalEntity;

import java.util.List;

public interface MapperRentalService {

    RentalEntity map(RentalDto rentalDto);
    RentalDto map(RentalEntity rentalEntity);
    List<RentalDto> map(List<RentalEntity> rentalEntities);
    List<RentalEntity> map2(List<RentalDto> rentalDtos);
}
