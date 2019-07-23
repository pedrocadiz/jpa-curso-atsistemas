package com.example.entrega.service;


import com.example.entrega.model.ClientEntity;

import java.util.List;

public interface ClientService {
    ClientEntity save (ClientEntity clientEntity);
    List<ClientEntity> save(List<ClientEntity> clientEntities);
    List<ClientEntity> showAll();
    ClientEntity findClientEntityById(Integer id);
    Boolean equals(ClientEntity clientEntity1, ClientEntity clientEntity2);
    void delete(ClientEntity clientEntity);
}
