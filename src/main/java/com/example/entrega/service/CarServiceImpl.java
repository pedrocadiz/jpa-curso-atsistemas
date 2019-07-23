package com.example.entrega.service;

import com.example.entrega.dao.CarDao;
import com.example.entrega.model.CarEntity;
import com.example.entrega.model.RateEntity;
import com.example.entrega.model.RentalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class CarServiceImpl implements CarService {

    @Autowired private CarDao carDao;
    @Autowired private RateService rateService;
    @Autowired private RentalService rentalService;

    //TODO: comprobar que la matricula del coche no existe al guardar
    //TODO: si hay cambio de rate no funciona bien el profit


    @Override
    public CarEntity saveCar (CarEntity carEntity){
        return carDao.save(carEntity);
    }

    @Override
    public List<CarEntity> showAll(){
        return carDao.findAll();
    }

    @Override
    public CarEntity findCarEntityById(Integer id){

        return carDao.findCarEntityById(id);
    }

    @Override
    public Boolean equals(CarEntity carEntity1, CarEntity carEntity2) {

        if(!carEntity1.getId().equals(carEntity2.getId())) return false;
        if(!carEntity1.getCarPlate().equals(carEntity2.getCarPlate()) ) return false;
        if(!carEntity1.getRegistrationYear().equals(carEntity2.getRegistrationYear())) return false;

        return true;
    }

    @Override
    public CarEntity singUpRate(CarEntity carEntity, Integer rateEntityId) {
        // Comprobamos que existe el RateEntity indicado
        if(rateService.findRateEntityById(rateEntityId) == null ) return null;
        RateEntity rateEntity = rateService.findRateEntityById(rateEntityId);

        // Comprobamos que el nuevo rango no solapa con los antiguos
        LocalDate startNewRange = rateEntity.getStartDate();
        LocalDate endNewRange = rateEntity.getEndDate();

        for (RateEntity b: carEntity.getRateEntities()){
            if(!validRange(b.getStartDate(),b.getEndDate(),startNewRange,endNewRange)) return null;
        }

        // Actualizamos carEntity
        Set<RateEntity> tempSetEntities = carEntity.getRateEntities();
        tempSetEntities.add(rateEntity);
        carEntity.setRateEntities(tempSetEntities);

        return carDao.save(carEntity);
    }

    @Override
    public Boolean validRange(LocalDate startOldRange, LocalDate endOldRange, LocalDate startNewRange, LocalDate endNewRange) {
        // validacion fecha en el orden correcto
        if(startNewRange.isAfter(endNewRange)) return false;

        // validacion fuera del antiguo rango
        if(!outOfRange(startOldRange,endOldRange,startNewRange)) return false;
        if(!outOfRange(startOldRange,endOldRange,endNewRange)) return false;

        // validacion ambos en el mismo lado del rango
        if(startNewRange.isBefore(startOldRange) && endNewRange.isAfter(endOldRange)) return false;

        return true;
    }

    @Override
    public Boolean outOfRange(LocalDate startRange, LocalDate endRange, LocalDate day) {
        if(!(day.isBefore(startRange) || day.isAfter(endRange))) return false;
        return true;
    }

    @Override
    public Double profit(CarEntity carEntity) {
        List<RentalEntity> rentalEntities = rentalService.findRentalEntitybyCarEntity(carEntity);
        Double profit = 0.0;


        for(RentalEntity b : rentalEntities){
            LocalDate startDay = b.getStartDate();
            LocalDate lastDay = b.getEndDate();
            Long totalDays = DAYS.between(startDay, lastDay);

            profit = profit + b.getPrice() * totalDays;
        }
        return profit;
    }
    public CarEntity profit(List<CarEntity> carsEntities) {

        Integer idMostProfitable = 0;
        Double maximumProfit = -1.0;

        for(CarEntity b : carsEntities) {
            Double actualProfit = profit(b);
            if( actualProfit > maximumProfit){
                idMostProfitable = b.getId();
                maximumProfit = actualProfit;
            }
        }

        return findCarEntityById(idMostProfitable);
    }


    @Override
    public List<CarEntity> saveCar(List<CarEntity> carEntities){
        for (CarEntity b : carEntities){
            saveCar(b);
        }
        return carEntities;
    }

    @Override
    public void delete(CarEntity carEntity){
            carDao.delete(carEntity);
    }
}
