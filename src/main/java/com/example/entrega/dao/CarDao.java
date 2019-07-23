package com.example.entrega.dao;

import com.example.entrega.model.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarDao extends JpaRepository<CarEntity, Integer>{
    List<CarEntity> findAll();
    CarEntity findCarEntityById(Integer id);
}
