package com.example.entrega.service;

import com.example.entrega.model.CarEntity;
import com.example.entrega.model.RentalEntity;

import java.time.LocalDate;
import java.util.List;

public interface RentalService {
    RentalEntity save(RentalEntity rentalEntity);
    List<RentalEntity> save(List<RentalEntity> rentalEntities);
    List<RentalEntity> showAll();
    RentalEntity findRentalEntityById(Integer id);
    Boolean carDaysAvailable(Integer idCar, LocalDate starDay, LocalDate lastDay);
    List<RentalEntity> findRentalEntitybyCarEntity(CarEntity carEntity);
    void delete(RentalEntity rentalEntity);
}
