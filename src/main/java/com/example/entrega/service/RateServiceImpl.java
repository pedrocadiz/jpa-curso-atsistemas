package com.example.entrega.service;

import com.example.entrega.dao.RateDao;
import com.example.entrega.model.RateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RateServiceImpl implements RateService{

    @Autowired private RateDao rateDao;
    @Autowired private CarService carService;

    @Override
    public RateEntity save(RateEntity rateEntity){
        return rateDao.save(rateEntity);
    }

    @Override
    public List<RateEntity> save(List<RateEntity> rateEntities) {
        for (RateEntity b : rateEntities){
            save(b);
        }
        return rateEntities;
    }

    @Override
    public List<RateEntity> showAll() {
        return rateDao.findAll();
    }

    @Override
    public RateEntity findRateEntityById(Integer id) {
        return rateDao.findRateEntityById(id);
    }

    @Override
    public Double priceDay(LocalDate day) {
        List<RateEntity> rateEntities = rateDao.findAll();

        for (RateEntity b : rateEntities) {
            //TODO: queda feo usarlo asi pero no me da tiempo
            if (!carService.outOfRange(b.getStartDate(), b.getEndDate(), day)) {
                return b.getPrice();
            }
        }
        return null;
    }

    @Override
    public void delete(RateEntity rateEntity) {
        rateDao.delete(rateEntity);
    }


}
