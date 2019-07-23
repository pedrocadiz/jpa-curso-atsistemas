package com.example.entrega.service;

import com.example.entrega.dao.RentalDao;
import com.example.entrega.model.CarEntity;
import com.example.entrega.model.RentalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired private RentalDao rentalDao;
    @Autowired private CarService carService;
    @Autowired private ClientService clientService;
    @Autowired private RateService rateService;



    @Override
    public RentalEntity save(RentalEntity rentalEntity) {
        // Comprobamos que existan ambos campos en la BD y sean exactamente el mismo
        if(carService.findCarEntityById(rentalEntity.getCarEntity().getId()) == null) return null;
        if(!carService.equals(
                rentalEntity.getCarEntity(),
                carService.findCarEntityById(rentalEntity.getCarEntity().getId())
                             )
          ) return null;

        if(clientService.findClientEntityById(rentalEntity.getClientEntity().getId()) == null) return null;
        if(!clientService.equals(
                rentalEntity.getClientEntity(),
                clientService.findClientEntityById(rentalEntity.getClientEntity().getId())
                                )
        ) return null;

        // Comprobamos que el coche esta libre esos dias
        if(!carDaysAvailable(
                carService.findCarEntityById(rentalEntity.getCarEntity().getId()).getId(),
                rentalEntity.getStartDate(),
                rentalEntity.getEndDate()
                )
        ) return null;

        // TODO: comprobamos que esos dias exista un rate para ese coche
        // TODO: faltaria separar en varios tramos de los diferentes rates posibles

        rentalEntity.setPrice(rateService.priceDay(rentalEntity.getStartDate()));
        return rentalDao.save(rentalEntity);
    }

    @Override
    public List<RentalEntity> save(List<RentalEntity> rentalEntities) {
        for (RentalEntity b : rentalEntities){
            save(b);
        }
        return rentalEntities;
    }

    @Override
    public List<RentalEntity> showAll() {
        return rentalDao.findAll();
    }

    @Override
    public RentalEntity findRentalEntityById(Integer id) {
        return rentalDao.findRentalEntityById(id);
    }
    @Override
    public Boolean carDaysAvailable(Integer idCar, LocalDate starDay, LocalDate lastDay) {
        // Comprobamos que existe el entity de car
        if(carService.findCarEntityById(idCar) == null) return false;

        // Recuperamos todas las fechas donde esta alquilado el coche
        CarEntity carEntity = carService.findCarEntityById(idCar);
        List<RentalEntity> rentalEntities = rentalDao.findRentalEntitiesByCarEntity_CarPlate(carEntity.getCarPlate());

        // Comprobamos que no esta alquilado ningun dia seleccionado
        lastDay = lastDay.plus(1, ChronoUnit.DAYS); // Tiene que ser un dia mas del que queremos por el isBefore
        for (RentalEntity b : rentalEntities){
            LocalDate tempDay = starDay;

            while(tempDay.isBefore(lastDay)) {
                if (!carService.outOfRange(b.getStartDate(), b.getEndDate(), tempDay)) return false;
                tempDay = tempDay.plus(1, ChronoUnit.DAYS);
            }
        }

        return true;
    }

    @Override
    public List<RentalEntity> findRentalEntitybyCarEntity(CarEntity carEntity) {
        return rentalDao.findRentalEntitiesByCarEntity(carEntity);
    }


    @Override
    public void delete(RentalEntity rentalEntity) {
        rentalDao.delete(rentalEntity);

    }
}
