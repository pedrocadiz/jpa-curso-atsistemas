package com.example.entrega.controller;


import com.example.entrega.component.MapperRateService;
import com.example.entrega.dto.RateDto;
import com.example.entrega.model.RateEntity;
import com.example.entrega.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rate")
public class RateController {
    @Autowired private RateService rateService;
    @Autowired private MapperRateService mapperRateService;

    @PostMapping
    public RateDto createFromDto(@RequestBody RateDto rateDto){
        // Comprobamos que no hay id existente
        if(rateDto.getId() != null) return null;

        RateEntity rateEntity = mapperRateService.map(rateDto);
        return mapperRateService.map(rateService.save(rateEntity));
    }

    @PostMapping("/list")
    public List<RateDto> createFromDto(@RequestBody List<RateDto> ratesDto){
        // Comprobamos que no hay id
        for (RateDto b : ratesDto){
            if(b.getId() != null) return null;
        }

        List<RateEntity> rateEntities = mapperRateService.map2(ratesDto);
        return mapperRateService.map(rateService.save(rateEntities));
    }

    @GetMapping
    public List<RateDto> showAll(){
        List<RateEntity> rateEntities = rateService.showAll();
        return mapperRateService.map(rateEntities);
    }

    @GetMapping("/{id}")
    public RateDto findOne(@PathVariable("id") Integer id){
        RateEntity rateEntity = rateService.findRateEntityById(id);
        return mapperRateService.map(rateEntity);
    }
    @PutMapping
    public RateDto modify(@RequestBody RateDto rateDto){
        // Comprobamos que nos han pasado un id
        if(rateDto.getId() == null) return null;

        RateEntity rateEntity = mapperRateService.map(rateDto);
        // Y que hay un id para cambiar
        if(rateService.findRateEntityById(rateEntity.getId()) == null) return null;

        return mapperRateService.map(rateService.save(rateEntity));
    }
    @DeleteMapping
    public void delete(@RequestBody RateDto rateDto){
        RateEntity rateEntity = mapperRateService.map(rateDto);
        rateService.delete(rateEntity);
    }

}
