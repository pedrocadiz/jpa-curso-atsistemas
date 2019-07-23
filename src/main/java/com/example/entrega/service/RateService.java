package com.example.entrega.service;

import com.example.entrega.model.CarEntity;
import com.example.entrega.model.RateEntity;

import java.time.LocalDate;
import java.util.List;

public interface RateService {

    RateEntity save(RateEntity rateEntity);
    List<RateEntity> save(List<RateEntity> rateEntities);
    List<RateEntity> showAll();
    RateEntity findRateEntityById(Integer id);
    Double priceDay(LocalDate day);
    void delete(RateEntity rateEntity);
}
