package com.example.entrega.service;

import com.example.entrega.model.CarEntity;


import java.time.LocalDate;
import java.util.List;

public interface CarService {
    CarEntity saveCar (CarEntity carEntity);
    List<CarEntity> saveCar(List<CarEntity> carEntities);
    List<CarEntity> showAll();
    CarEntity findCarEntityById(Integer id);
    Boolean equals(CarEntity carEntity1, CarEntity carEntity2);
    CarEntity singUpRate (CarEntity carEntity, Integer rateEntityId);
    Boolean validRange(LocalDate startOldRange, LocalDate endOldRange, LocalDate startNewRange, LocalDate endNewRange);
    Boolean outOfRange(LocalDate startRange, LocalDate endRange, LocalDate day);
    Double profit(CarEntity carEntity);
    CarEntity profit(List<CarEntity> carEntity);
    void delete(CarEntity carEntity);

}

