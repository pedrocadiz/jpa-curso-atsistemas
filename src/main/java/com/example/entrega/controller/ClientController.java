package com.example.entrega.controller;

import com.example.entrega.component.MapperClientService;
import com.example.entrega.dto.ClientDto;
import com.example.entrega.model.ClientEntity;
import com.example.entrega.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired private ClientService clientService;
    @Autowired private MapperClientService mapperClientService;

    @PostMapping
    public ClientDto createFromDto(@RequestBody ClientDto clientDto){
        // Comprobamos que no hay id existente
        if(clientDto.getId() != null) return null;

        ClientEntity clientEntity = mapperClientService.map(clientDto);
        return mapperClientService.map(clientService.save(clientEntity));
    }

    @PostMapping("/list")
    public List<ClientDto> createFromDto(@RequestBody List<ClientDto> clientsDto){
        // Comprobamos que no hay id
        for (ClientDto b : clientsDto){
            if(b.getId() != null) return null;
        }

        List<ClientEntity> clientEntities = mapperClientService.map2(clientsDto);
        return mapperClientService.map(clientService.save(clientEntities));
    }

    @GetMapping
    public List<ClientDto> showAll(){
        List<ClientEntity> clientEntities = clientService.showAll();
        return mapperClientService.map(clientEntities);
    }

    @GetMapping("/{id}")
    public ClientDto findOne(@PathVariable("id") Integer id){
        ClientEntity clientEntity = clientService.findClientEntityById(id);
        return mapperClientService.map(clientEntity);
    }

    @PutMapping
    public ClientDto modify(@RequestBody ClientDto clientDto){
        // Comprobamos que nos han pasado un id
        if(clientDto.getId() == null) return null;

        ClientEntity clientEntity = mapperClientService.map(clientDto);
        // Y que hay un id para cambiar
        if(clientService.findClientEntityById(clientEntity.getId()) == null) return null;

        return mapperClientService.map(clientService.save(clientEntity));
    }

    @DeleteMapping
    public void delete(@RequestBody ClientDto clientDto){
        ClientEntity clientEntity = mapperClientService.map(clientDto);
        clientService.delete(clientEntity);
    }


}
