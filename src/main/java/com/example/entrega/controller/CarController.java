package com.example.entrega.controller;


import com.example.entrega.component.MapperCarService;
import com.example.entrega.dto.CarDto;
import com.example.entrega.model.CarEntity;
import com.example.entrega.service.CarService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired private CarService carService;
    @Autowired private MapperCarService mapperCarService;

    //TODO: Que la carPlate sea unica

    @PostMapping
    public CarDto createCarFromDto(@RequestBody CarDto carDto){
        // Comprobamos que no hay id
        if(carDto.getId() != null) return null;

        CarEntity carEntity = mapperCarService.map(carDto);
        return mapperCarService.map(carService.saveCar(carEntity));

    }

    @PostMapping("/list")
    public List<CarDto> createCarFromDto(@RequestBody List<CarDto> carsDto){
        // Comprobamos que no hay id
        for (CarDto b : carsDto){
            if(b.getId() != null) return null;
        }

        List<CarEntity> carEntities = mapperCarService.map2(carsDto);
        return mapperCarService.map(carService.saveCar(carEntities));
    }


    @GetMapping
    public List<CarDto> showAll(){
        List<CarEntity> carEntities = carService.showAll();
        return mapperCarService.map(carEntities);
    }

    @GetMapping("/{id}")
    public CarDto findOne(@PathVariable("id") Integer id) throws NotFoundException{

        return Optional.ofNullable(id)
                .map(x -> carService.findCarEntityById(x))
                .map(x -> mapperCarService.map(x))
                .orElseThrow( () -> new NotFoundException("No existe en la BD ese id"));
    }

    @PutMapping("/{id}/rate/{idRate}")
    public CarDto singUp(@PathVariable("id") Integer id,@PathVariable("idRate") Integer idRate) throws NotFoundException{
        if(carService.findCarEntityById(id) == null) return null;

        return Optional.ofNullable(carService.singUpRate(carService.findCarEntityById(id),idRate))
                .map( x -> mapperCarService.map(x))
                .orElseThrow( () -> new NotFoundException("Conflicto con la BD"));
    }

    @GetMapping("/{id}/profit")
    public Double profitCar(@PathVariable("id") Integer id) throws NotFoundException{
        return Optional.ofNullable(carService.findCarEntityById(id))
                .map(x -> carService.profit(x))
                .orElseThrow( () -> new NotFoundException("Conflicto con la BD"));
    }

    @GetMapping("/profit")
    public CarDto profit() throws NotFoundException{

        return Optional.ofNullable(carService.showAll())
                .map(x -> carService.profit(x))
                .map(x -> mapperCarService.map(x))
                .orElseThrow( () -> new NotFoundException("Conflicto con la BD"));

    }

    @PutMapping
    public CarDto modify(@RequestBody CarDto carDto){
        // Comprobamos que nos han pasado un id
        if(carDto.getId() == null) return null;

        CarEntity carEntity = mapperCarService.map(carDto);
        // Y que hay un id para cambiar
        if(carService.findCarEntityById(carEntity.getId()) == null) return null;

        return mapperCarService.map(carService.saveCar(carEntity));
    }

    @DeleteMapping
    public void delete(@RequestBody CarDto carDto){
        CarEntity carEntity = mapperCarService.map(carDto);
        carService.delete(carEntity);
    }
}