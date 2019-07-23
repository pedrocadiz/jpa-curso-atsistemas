package com.example.entrega.dao;

import com.example.entrega.model.CarEntity;
import com.example.entrega.model.RateEntity;
import com.example.entrega.model.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalDao extends JpaRepository<RentalEntity, Integer> {
    List<RentalEntity> findAll();
    RentalEntity findRentalEntityById(Integer id);
    List<RentalEntity> findRentalEntitiesByCarEntity_CarPlate(String carPlate);
    List<RentalEntity> findRentalEntitiesByCarEntity(CarEntity carEntity);
}