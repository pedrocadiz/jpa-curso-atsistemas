package com.example.entrega.controller;


import com.example.entrega.component.MapperRentalService;
import com.example.entrega.dto.RentalDto;
import com.example.entrega.model.RentalEntity;
import com.example.entrega.service.RentalService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rental")
public class RentalController {

    @Autowired private RentalService rentalService;
    @Autowired private MapperRentalService mapperRentalService;

    @PostMapping
    public RentalDto createFromDto(@RequestBody RentalDto rentalDto) throws NotFoundException {
        // Comprobamos que no hay id
        if(rentalDto.getId() != null) return null;
        RentalEntity rentalEntity = mapperRentalService.map(rentalDto);

        return Optional.ofNullable(rentalEntity)
                .map(x -> rentalService.save(x))
                .map(x -> mapperRentalService.map(x))
                .orElseThrow( () -> new NotFoundException("Inconsistencia con la BBDD"));
    }

    @PostMapping("/list")
    public List<RentalDto> createFromDto(@RequestBody List<RentalDto> rentalsDto){
        // Comprobamos que no hay id
        for (RentalDto b : rentalsDto){
            if(b.getId() != null) return null;
        }

        List<RentalEntity> rentalEntities = mapperRentalService.map2(rentalsDto);
        return mapperRentalService.map(rentalService.save(rentalEntities));
    }

    @GetMapping
    public List<RentalDto> showAll(){
        List<RentalEntity> rentalEntities = rentalService.showAll();
        return mapperRentalService.map(rentalEntities);
    }

    @GetMapping("/{id}")
    public RentalDto findOne(@PathVariable("id") Integer id){
        RentalEntity rentalEntity = rentalService.findRentalEntityById(id);
        return mapperRentalService.map(rentalEntity);
    }

    @PutMapping
    public RentalDto modify(@RequestBody RentalDto rentalDto){
        // Comprobamos que nos han pasado un id
        if(rentalDto.getId() == null) return null;

        RentalEntity rentalEntity = mapperRentalService.map(rentalDto);
        // Y que hay un id para cambiar
        if(rentalService.findRentalEntityById(rentalEntity.getId()) == null) return null;

        return mapperRentalService.map(rentalService.save(rentalEntity));
    }

    @DeleteMapping
    public void delete(@RequestBody RentalDto rentalDto){
        RentalEntity rentalEntity = mapperRentalService.map(rentalDto);
        rentalService.delete(rentalEntity);
    }

}
