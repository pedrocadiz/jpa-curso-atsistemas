package com.example.entrega.dao;

import com.example.entrega.model.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateDao extends JpaRepository<RateEntity, Integer> {
    List<RateEntity> findAll();
    RateEntity findRateEntityById(Integer id);
}
